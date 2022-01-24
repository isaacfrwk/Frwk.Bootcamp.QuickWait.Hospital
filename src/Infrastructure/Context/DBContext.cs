using FrwkBootCampQuickWait.Hospital.Domain.Contracts;
using FrwkBootCampQuickWait.Hospital.Domain.Entities;
using FrwkBootCampQuickWait.Hospital.Infrastructure.HospitalContext;
using Microsoft.EntityFrameworkCore;

namespace FrwkBootCampQuickWait.Hospital.Infrastructure.Context
{
    public class DBContext : DbContext, IDbContext
    {
        public DBContext()
        {
        }

        public DBContext(DbContextOptions<DBContext> options) : base(options)
        {
        }

        public virtual DbSet<Domain.Entities.Hospital> Hospital { get; set; }

        protected override void OnModelCreating(ModelBuilder builder)
        {
            builder.ApplyConfiguration(new HospitalMap());
        }
    }
}