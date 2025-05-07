## to use 2 database simartaniously we need to follow::
1. create 2 seperate **datasource** in .properties.
2. create 2 different packages
3. And in 2 packages we need to create seperate entities, repositories, config file for every database

#### Properties:
```properties

#H2
spring.db2.datasouce.jdbcUrl = jdbc:h2:mem:testdb
spring.db2.datasouce.username = sa
spring.db2.datasouce.password = sa
spring.db2.datasouce.driver-class-name = org.h2.Driver

#Oracle
spring.db1.datasource.jdbcUrl=jdbc:oracle:thin:@//10.51.13.31:1521/pouatdb_pdb1.gsidcnpdbprvsn.gsidcspkvcn.oraclevcn.com
spring.db1.datasource.username=sit_egov
spring.db1.datasource.password=sit_egov
spring.db1.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```
#### configeration
```java
@Configuaration
@EnableTransactionManagement
@EnableJpaReporsitories(
  entityManagerFactoryRef ="entityManagerFactory",
  basePackages ={
    "in.joydeb.db1.repositories"
  }
)
public class Db1Config{

  @Primary
  @Bean
  @ConfigurationProperties(prefix = "spring.db1.datasource")
  public DataSource dataSource(){
    retrun DataSourceBuilder.create().build();
  }

  @Primary
  @Bean(name="entityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
    @Qualifer("datasource") DataSource dataSource
  ){
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.hbm2ddl.auto","update");
    return builder.dataSource(dataSource)
                  .properties(properties)
                  .packages("in.joydeb.db1.entities")
                  .persistenceUnit("db1")
                  .build();
  }

  @Primary
  @Bean(name="transactionManager")
  public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory){
    return new JpaTranactionManager(entityManagerFactory)
  }
  
}
```
