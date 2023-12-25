Task description:

"Given two threads - a producer and a consumer. The producer generates various types of data:

- Integer numbers
- Floating-point numbers
- Strings
- Serialized/deserialized POJOs

The consumer 'consumes' these data types.

These two threads share a common data buffer with a limited size. If the buffer is empty, the consumer must wait until data appears. If the buffer is completely filled, the producer must wait until the consumer retrieves the data and frees up space."