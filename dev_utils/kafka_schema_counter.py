#!/usr/bin/env python

from json_schema_generator.generator import SchemaGenerator
from collections import defaultdict
from kafka import KafkaConsumer


class SchemaCounter:
    """
    Takes a sample of JSON messages from a topic and creates a dictionary of schema:count.
    """

    def __init__(self):
        self.schema_count_dict = defaultdict(int)
        self.json = None
        self.consumer = KafkaConsumer('pmid-search',
                                      group_id='schema_counter',
                                      bootstrap_servers=['hdf2:6667'],
                                      auto_offset_reset='earliest')
        self.json_elements_to_analyze = 1000
        self.json_elements_analyzed = 0

    def parse_schemas_from_kafka(self):
        for message in self.consumer:
            generator = SchemaGenerator.from_json(message.value)
            schema_json = generator.to_json()
            self.schema_count_dict[schema_json] += 1
            self.json_elements_analyzed += 1
            print "documents analyzed: {0}".format(self.json_elements_analyzed)
            if self.json_elements_analyzed >= self.json_elements_to_analyze:
                break

    def get_schema_count_dict(self):
        return self.schema_count_dict


if __name__ == "__main__":
    schema_counter = SchemaCounter()
    schema_counter.parse_schemas_from_kafka()
    schema_count_dict = schema_counter.get_schema_count_dict()
    del schema_counter
