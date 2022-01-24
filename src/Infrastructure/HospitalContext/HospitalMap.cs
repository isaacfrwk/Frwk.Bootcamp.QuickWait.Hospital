using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace FrwkBootCampQuickWait.Hospital.Infrastructure.HospitalContext
{
    public class HospitalMap : IEntityTypeConfiguration<Domain.Entities.Hospital>
    {
        public void Configure(EntityTypeBuilder<Domain.Entities.Hospital> builder)
        {
            builder.ToTable("Hospital");
            builder.HasKey(x => x.Id);
        }
    }
}