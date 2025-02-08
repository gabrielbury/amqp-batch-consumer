FROM rabbitmq:4.0.5-management-alpine

ADD ./rabbitmq.conf /etc/rabbitmq/
ADD ./definitions.json /etc/rabbitmq/

# Enable RabbitMQ management plugin before startup
RUN rabbitmq-plugins enable --offline rabbitmq_management

# Set environment variables for RabbitMQ
ENV RABBITMQ_CONFIG_FILE=/etc/rabbitmq/rabbitmq \
  RABBITMQ_LOAD_DEFINITIONS=/etc/rabbitmq/definitions.json

# Expose necessary ports
EXPOSE 5672 15672

# Start RabbitMQ
CMD ["rabbitmq-server"]