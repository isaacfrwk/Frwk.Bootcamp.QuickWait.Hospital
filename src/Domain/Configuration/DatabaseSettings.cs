namespace FrwkBootCampQuickWait.Hospital.Domain.Configuration
{
    public sealed class DatabaseSettings : Settings
    {
        public string ConnectionString { get; set; }
        public string Database { get; set; }
    }
}
