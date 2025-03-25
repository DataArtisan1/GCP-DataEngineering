# This will not work in Windows Environment as there is some issues with the Windows path. We need to run from WSL/Unix Based environment

import apache_beam as beam
from apache_beam.options.pipeline_options import PipelineOptions, StandardOptions
import logging

# Setup logging
logging.basicConfig(level=logging.INFO)

def run():
    # Define pipeline options
    options = PipelineOptions()

    # Set the runner to SparkRunner
    standard_options = options.view_as(StandardOptions)
    standard_options.runner = 'SparkRunner'

    # Create the Beam pipeline
    with beam.Pipeline(options=options) as p:
        # Create a PCollection of numbers
        numbers = p | 'CreateNumbers' >> beam.Create(range(1, 50))

        # Square each number
        squares = numbers | 'SquareNumbers' >> beam.Map(lambda x: x * x)

        # Log the squared numbers
        squares | 'LogSquaredNumbers' >> beam.Map(lambda x: logging.info(f'Squared number: {x}'))

        # Print final results
        squares | 'PrintResults' >> beam.Map(print)

if __name__ == '__main__':
    run()
