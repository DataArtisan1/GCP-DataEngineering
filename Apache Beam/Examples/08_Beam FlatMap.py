import apache_beam as beam

with beam.Pipeline() as p:
    sentences = ['this is a sentence', 'beam is fun']

    # Split each sentence into words
    words = (
        p
        | 'Create sentences' >> beam.Create(sentences)
        | 'FlatMap words' >> beam.FlatMap(lambda sentence: sentence.split())
        | 'Print words' >> beam.Map(print)
    )
