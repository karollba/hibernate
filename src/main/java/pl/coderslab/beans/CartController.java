package pl.coderslab.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

@Controller
public class CartController {

    @Autowired
    private Cart cart;

    @Autowired
    private ProductDao productDao;

    @RequestMapping("/addtocart")
    @ResponseBody
    public String addtocart(@RequestParam Long id, @RequestParam Integer quantity) {

        Product product = productDao.getList().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (product == null) {
            return "Nie znaleziono produktu o id: " + id;
        }

        CartItem exists = cart.getCartItems().stream()
                        .filter(i -> i.getProduct().getId().equals(id))
                        .findFirst()
                        .orElse(null);

        if (exists != null) {
            exists.setQuantity(exists.getQuantity() + quantity);
        } else {
            cart.addToCart(new CartItem(quantity, product));

        }

        return "Dodano do ksozyka: " + product.getName() + " ilość: " + quantity;

//        cart.addToCart(new CartItem(quantity, product));

//        Random rand = new Random();
//        cart.addToCart(new CartItem(1, new Product("prod" + rand.nextInt(10), rand.nextDouble())));
//        return "addtocart";
    }


    @RequestMapping("/cart")
    @ResponseBody
    public String showCart() {
//        StringBuilder stringBuilder = new StringBuilder();
//        for (CartItem item : cart.getCartItems()) {
//            stringBuilder.append(item.toString()).append("</br>");
//        }
//        return stringBuilder.toString();

        int positionsQuantity = cart.getCartItems().size();
        int productsQuantity = cart.getCartItems().stream()
                .mapToInt(CartItem::getQuantity)
                .sum();

        double cartSum = cart.getCartItems().stream()
                .mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity())
                .sum();

        String productType = cart.getCartItems().stream()
                .map(i -> i.getProduct().nameToString())
                .collect(Collectors.joining(", "));

        return "W koszyku jest " + positionsQuantity + " pozycji." + " typ produktu/ów: " + productType + "</br>" +
                " W koszyku jest " + productsQuantity + " produktów." + "</br>" +
                "Wartość koszyka to: " + String.format("%.2f", cartSum) + " PLN";
    }
}
