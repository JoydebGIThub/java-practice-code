### Put
```java
@PutMapping("/{id}")
public Product updateProduct(@PathVariable int id, @RequestBody Product productRequest){
  return service.updateProduct(id, productRequest);
}
```
```json
{
  "name": "Mixer Grinder",
  "price": 9000,
  "description": "Bosch",
  "productType": "Kitchen & Home"
}
```
We need to give the **whole body** in RequestBody for put request, if we remove one of the key value pair then a default value (like null, 0) will be saved for that particular key
### Patch
here we can pass the **part we need to update** in the RequestBody.
```java
@Autowired
private ProductService service;

@PatchMapping("/{id}")
public Product updateProductFields(@PathVariable int id, @RequestBody Map<String, Object> fields){
  return service.updateProductFields(id, fields);
}
```
#### Service
```java
import org.springframework.util.*;
@Service
public class ProductService{
  @Autowired
  private ProductRepository repository;

  public Product updateProductFields(int id, Map<String, Object> fields){
    //Product existingProduct = repository.findById(id).get(); // give the existing data
    Optional<Product> existingProduct = repository.findById(id);
    if(existingProduct.isPresent()){
      fields.forEach((key, value)->{
         Field field= ReflectionUtils.findField(Product, key);
          field.setAccessible(true);
          ReflectionUtils.setField(field, existingProduct.get(), value);
      });
      retrun repository.save(existingProduct.get());
    }
    return null; 
  }
}

```

```json
{
  "price": 9000
}
```
