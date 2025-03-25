import mysql.connector
import apache_beam as beam
from apache_beam.options.pipeline_options import PipelineOptions

def read_from_mysql():
    # Establish the connection
    conn = mysql.connector.connect(
        host='localhost',
        user='root',  # Your MySQL username
        password='password',  # Your MySQL password
        database='sample'  # Your database name
    )
    
    cursor = conn.cursor()
    cursor.execute("SELECT * FROM students")
    
    # Fetch all rows
    rows = cursor.fetchall()
    
    # Close the cursor and connection
    cursor.close()
    conn.close()
    
    return rows

class PrintRowFn(beam.DoFn):
    def process(self, element):
        print(element)
        yield element

def run():
    # Define pipeline options for local execution
    options = PipelineOptions(
        runner='DirectRunner',  # Use DataflowRunner for Google Cloud Dataflow
    )

    with beam.Pipeline(options=options) as p:
        # Read from MySQL using the read_from_mysql function
        rows = (
            p
            | 'Create' >> beam.Create(read_from_mysql())  # Using the read_from_mysql function
            | 'PrintRows' >> beam.ParDo(PrintRowFn())
        )

if __name__ == '__main__':
    run()
