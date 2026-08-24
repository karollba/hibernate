package pl.coderslab.beans;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductDao {
  private List<Product> products = List.of(
          new Product("Laptop", 3999.99, 1L),
          new Product("Monitor", 799.99, 2L),
          new Product( "Tablet", 1200.99, 3L),
          new Product( "telefon", 4599.99, 3L)
      );

  public List<Product> getList() {
      return products;
  }
}
