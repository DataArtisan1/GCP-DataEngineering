import apache_beam as beam

class Person:
    def __init__(self, name, age):
        self.name = name
        self.age = age

with beam.Pipeline() as p:
    # Create a PCollection of Person objects
    people = p | 'Create objects' >> beam.Create([Person('Alice', 25), Person('Bob', 30)])
    
    # Print each person's details
    people | 'Print people' >> beam.Map(lambda person: print(f'{person.name}, {person.age}'))
