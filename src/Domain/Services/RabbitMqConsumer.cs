using FrwkBootCampQuickWait.Hospital.Domain.Configuration;
using FrwkBootCampQuickWait.Hospital.Domain.Extensions;
using FrwkBootCampQuickWait.Hospital.Domain.Services.Factories;
using MediatR;
using RabbitMQ.Client;
using RabbitMQ.Client.Events;

namespace FrwkBootCampQuickWait.Hospital.Domain.Services
{
    public abstract class RabbitMqConsumer
    {
        private readonly ConnectionFactory _connectionFactory;
        private readonly IConnection _connection;
        private readonly IModel _channel;
        private readonly RabbitSettings _rabbitSettings;
        private readonly IMediator _mediator;

        protected abstract string ContextQueue { get;}

        protected RabbitMqConsumer(RabbitSettings rabbitSettings, IMediator mediator)
        {
            _connectionFactory = new ConnectionFactory() { HostName = rabbitSettings.Host };
            _connection = _connectionFactory.CreateConnection();
            _channel = _connection.CreateModel();
            _rabbitSettings = rabbitSettings;
            _mediator = mediator;
        }

        public async Task ConsumeAsync<TCommand>(string topicName, string queueName) where TCommand : IRequest<bool>
        {
            var queue = $"{_rabbitSettings.Queue}.{ContextQueue}.{queueName}";
            var topic = $"{_rabbitSettings.Queue}.{ContextQueue}.{topicName}";

            _channel.CreateQueue(queue);
            _channel.CreateTopic(topic);

            _channel.QueueBind(queue: queue,
                                  exchange: topic,
                                  routingKey: "");

            var consumer = new EventingBasicConsumer(_channel);

            consumer.Received += (model, ea) =>
            {
                var message = RabbitMqFactory.GetMessage(ea);
                var command = message.Payload.Parse<TCommand>();
                _mediator.Send(command);
            };

            _channel.BasicConsume(queue: queue,
                                 autoAck: true,
                                 consumer: consumer);

            await Task.CompletedTask;
        }
    }
}