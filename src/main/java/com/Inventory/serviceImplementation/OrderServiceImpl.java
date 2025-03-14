package com.Inventory.serviceImplementation;

import com.Inventory.entity.OrdersEntity;
import com.Inventory.entity.ProductEntity;
import com.Inventory.repository.OrdersRepo;
import com.Inventory.repository.ProductRepo;
import com.Inventory.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepo orderRepository;

    @Autowired
    private ProductRepo productRepository;

    @Override
    public List<OrdersEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<OrdersEntity> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

//    @Override
//    public OrdersEntity placeOrder(OrdersEntity order) {
//        // Check stock for each product before placing order
//        for (ProductEntity product : order.getProducts()) {
//            Optional<ProductEntity> productOptional = productRepository.findById(product.getId());
//
//            if (productOptional.isPresent()) {
//                ProductEntity existingProduct = productOptional.get();
//                if (existingProduct.getStockQuantity() <= 0) {
//                    throw new RuntimeException("Product " + existingProduct.getName() + " is out of stock!");
//                }
//                existingProduct.setStockQuantity(existingProduct.getStockQuantity() - 1); // Reduce stock
//                productRepository.save(existingProduct);
//            } else {
//                throw new RuntimeException("Product not found!");
//            }
//        }
//
//        return orderRepository.save(order);
//    }

    @Override
    public OrdersEntity updateOrder(Integer id, OrdersEntity updatedOrder) {
        Optional<OrdersEntity> existingOrder = orderRepository.findById(id);

        if (existingOrder.isPresent()) {
            updatedOrder.setId(id);
            return orderRepository.save(updatedOrder);
        } else {
            throw new RuntimeException("Order not found!");
        }
    }

    @Override
    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

	@Override
	public OrdersEntity placeOrder(OrdersEntity order) {
		// TODO Auto-generated method stub
		return null;
	}
}
