namespace FrwkBootCampQuickWait.Hospital.Domain.ValueObjects
{
    public sealed class Message
    {
        public object Payload { get; set; }
        public DateTime CreatedAt { get; set; }
    }
}
