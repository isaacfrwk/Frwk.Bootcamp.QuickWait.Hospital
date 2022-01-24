using RabbitMQ.Client;

namespace FrwkBootCampQuickWait.Hospital.Domain.Extensions
{
    public static class ModelExtensions
    {
        public static void CreateQueue(this IModel channel, string queueName)
        {
            channel.QueueDeclare(queueName, true, false, false);
        }

        public static void CreateTopic(this IModel channel, string topicName)
        {
            channel.ExchangeDeclare(topicName, "topic", true, false);
        }
    }
}
