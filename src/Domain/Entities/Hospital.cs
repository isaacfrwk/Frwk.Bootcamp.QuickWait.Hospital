using FrwkBootCampQuickWait.Hospital.Domain.Fixeds;
using FrwkBootCampQuickWait.Hospital.Domain.ValueObjects;

namespace FrwkBootCampQuickWait.Hospital.Domain.Entities
{
    public sealed class Hospital : Entity
    {
        public string Name { get; private set; }
        public Address Address { get; private set; }
        public HospitalSize Size { get; set; }
    }
}
