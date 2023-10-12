package com.Etech.Service.Impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Etech.Dto.OrderDto;
import com.Etech.Exception.ResourceException;
import com.Etech.Model.Address;
import com.Etech.Model.Admin;
import com.Etech.Model.CreditCard;
import com.Etech.Model.Customer;
import com.Etech.Model.Order;
import com.Etech.Model.Role;
import com.Etech.Model.enums.CustomerStatus;
import com.Etech.Model.enums.OrderStatus;
import com.Etech.Repository.AddressRepo;
import com.Etech.Repository.CustomerRepo;
import com.Etech.Repository.OrderRepo;
import com.Etech.Repository.ProductRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OrderServiceImpl.class})
@ExtendWith(SpringExtension.class)
class OrderServiceImplTest {
    @MockBean
    private AddressRepo addressRepo;

    @MockBean
    private CustomerRepo customerRepo;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private OrderRepo orderRepo;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @MockBean
    private ProductRepo productRepo;

    /**
     * Method under test: {@link OrderServiceImpl#placeOrder(Long, OrderDto)}
     */
    @Test
    void testPlaceOrder() {
        Role role = new Role();
        role.setId(1L);
        role.setRoleType("Role Type");

        Customer customer = new Customer();
        customer.setAddresses(new ArrayList<>());
        customer.setCreditCard(new CreditCard("Card Name", "42", "Card Expiration", "Card Cvv", "127.0.0.1"));
        customer.setCustomerStatus(CustomerStatus.ACTIVE);
        customer.setDateOfRegistration(LocalDate.ofEpochDay(1L));
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setPhone("6625550144");
        customer.setRole(role);
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepo.findById((Long) any())).thenReturn(ofResult);

        Admin admin = new Admin();
        admin.setEmail("jane.doe@example.org");
        admin.setFirstName("Jane");
        admin.setId(1L);
        admin.setLastName("Doe");
        admin.setPassword("iloveyou");

        Role role1 = new Role();
        role1.setId(1L);
        role1.setRoleType("Role Type");

        Customer customer1 = new Customer();
        customer1.setAddresses(new ArrayList<>());
        customer1.setCreditCard(new CreditCard("Card Name", "42", "Card Expiration", "Card Cvv", "127.0.0.1"));
        customer1.setCustomerStatus(CustomerStatus.ACTIVE);
        customer1.setDateOfRegistration(LocalDate.ofEpochDay(1L));
        customer1.setEmail("jane.doe@example.org");
        customer1.setFirstName("Jane");
        customer1.setId(1L);
        customer1.setLastName("Doe");
        customer1.setPassword("iloveyou");
        customer1.setPhone("6625550144");
        customer1.setRole(role1);

        Address address = new Address();
        address.setAdmin(admin);
        address.setCity("Oxford");
        address.setCustomer(customer1);
        address.setId(1L);
        address.setState("MD");
        address.setStreet("Street");
        address.setZipCode("21654");

        Role role2 = new Role();
        role2.setId(1L);
        role2.setRoleType("Role Type");

        Customer customer2 = new Customer();
        customer2.setAddresses(new ArrayList<>());
        customer2.setCreditCard(new CreditCard("Card Name", "42", "Card Expiration", "Card Cvv", "127.0.0.1"));
        customer2.setCustomerStatus(CustomerStatus.ACTIVE);
        customer2.setDateOfRegistration(LocalDate.ofEpochDay(1L));
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setId(1L);
        customer2.setLastName("Doe");
        customer2.setPassword("iloveyou");
        customer2.setPhone("6625550144");
        customer2.setRole(role2);

        Order order = new Order();
        order.setAddress(address);
        order.setCustomer(customer2);
        order.setId(1L);
        order.setOrderDate("2020-03-01");
        order.setOrderNumber(1L);
        order.setOrderStatus(OrderStatus.SUCCESS);
        order.setOrderTime("Order Time");
        order.setOrderTotal(10.0d);
        order.setProductCartItems(new ArrayList<>());
        when(modelMapper.map((Object) any(), (Class<Order>) any())).thenReturn(order);
        when(orderRepo.save((Order) any())).thenThrow(new ResourceException());
        assertThrows(ResourceException.class, () -> orderServiceImpl.placeOrder(1L, new OrderDto()));
        verify(customerRepo).findById((Long) any());
        verify(modelMapper).map((Object) any(), (Class<Order>) any());
        verify(orderRepo).save((Order) any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#placeOrder(Long, OrderDto)}
     */
    @Test
    void testPlaceOrder2() {
        when(customerRepo.findById((Long) any())).thenReturn(Optional.empty());
        Role role = mock(Role.class);
        doNothing().when(role).setId((Long) any());
        doNothing().when(role).setRoleType((String) any());
        role.setId(1L);
        role.setRoleType("Role Type");
        Customer customer = mock(Customer.class);
        when(customer.getCustomerStatus()).thenReturn(CustomerStatus.ACTIVE);
        doNothing().when(customer).setAddresses((List<Address>) any());
        doNothing().when(customer).setCreditCard((CreditCard) any());
        doNothing().when(customer).setCustomerStatus((CustomerStatus) any());
        doNothing().when(customer).setDateOfRegistration((LocalDate) any());
        doNothing().when(customer).setEmail((String) any());
        doNothing().when(customer).setFirstName((String) any());
        doNothing().when(customer).setId((Long) any());
        doNothing().when(customer).setLastName((String) any());
        doNothing().when(customer).setPassword((String) any());
        doNothing().when(customer).setPhone((String) any());
        doNothing().when(customer).setRole((Role) any());
        customer.setAddresses(new ArrayList<>());
        customer.setCreditCard(new CreditCard("Card Name", "42", "Card Expiration", "Card Cvv", "127.0.0.1"));
        customer.setCustomerStatus(CustomerStatus.ACTIVE);
        customer.setDateOfRegistration(LocalDate.ofEpochDay(1L));
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setPhone("6625550144");
        customer.setRole(role);

        Admin admin = new Admin();
        admin.setEmail("jane.doe@example.org");
        admin.setFirstName("Jane");
        admin.setId(1L);
        admin.setLastName("Doe");
        admin.setPassword("iloveyou");

        Role role1 = new Role();
        role1.setId(1L);
        role1.setRoleType("Role Type");

        Customer customer1 = new Customer();
        customer1.setAddresses(new ArrayList<>());
        customer1.setCreditCard(new CreditCard("Card Name", "42", "Card Expiration", "Card Cvv", "127.0.0.1"));
        customer1.setCustomerStatus(CustomerStatus.ACTIVE);
        customer1.setDateOfRegistration(LocalDate.ofEpochDay(1L));
        customer1.setEmail("jane.doe@example.org");
        customer1.setFirstName("Jane");
        customer1.setId(1L);
        customer1.setLastName("Doe");
        customer1.setPassword("iloveyou");
        customer1.setPhone("6625550144");
        customer1.setRole(role1);

        Address address = new Address();
        address.setAdmin(admin);
        address.setCity("Oxford");
        address.setCustomer(customer1);
        address.setId(1L);
        address.setState("MD");
        address.setStreet("Street");
        address.setZipCode("21654");

        Role role2 = new Role();
        role2.setId(1L);
        role2.setRoleType("Role Type");

        Customer customer2 = new Customer();
        customer2.setAddresses(new ArrayList<>());
        customer2.setCreditCard(new CreditCard("Card Name", "42", "Card Expiration", "Card Cvv", "127.0.0.1"));
        customer2.setCustomerStatus(CustomerStatus.ACTIVE);
        customer2.setDateOfRegistration(LocalDate.ofEpochDay(1L));
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setId(1L);
        customer2.setLastName("Doe");
        customer2.setPassword("iloveyou");
        customer2.setPhone("6625550144");
        customer2.setRole(role2);

        Order order = new Order();
        order.setAddress(address);
        order.setCustomer(customer2);
        order.setId(1L);
        order.setOrderDate("2020-03-01");
        order.setOrderNumber(1L);
        order.setOrderStatus(OrderStatus.SUCCESS);
        order.setOrderTime("Order Time");
        order.setOrderTotal(10.0d);
        order.setProductCartItems(new ArrayList<>());
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<Order>) any())).thenReturn(order);

        Admin admin1 = new Admin();
        admin1.setEmail("jane.doe@example.org");
        admin1.setFirstName("Jane");
        admin1.setId(1L);
        admin1.setLastName("Doe");
        admin1.setPassword("iloveyou");

        Role role3 = new Role();
        role3.setId(1L);
        role3.setRoleType("Role Type");

        Customer customer3 = new Customer();
        customer3.setAddresses(new ArrayList<>());
        customer3.setCreditCard(new CreditCard("Card Name", "42", "Card Expiration", "Card Cvv", "127.0.0.1"));
        customer3.setCustomerStatus(CustomerStatus.ACTIVE);
        customer3.setDateOfRegistration(LocalDate.ofEpochDay(1L));
        customer3.setEmail("jane.doe@example.org");
        customer3.setFirstName("Jane");
        customer3.setId(1L);
        customer3.setLastName("Doe");
        customer3.setPassword("iloveyou");
        customer3.setPhone("6625550144");
        customer3.setRole(role3);

        Address address1 = new Address();
        address1.setAdmin(admin1);
        address1.setCity("Oxford");
        address1.setCustomer(customer3);
        address1.setId(1L);
        address1.setState("MD");
        address1.setStreet("Street");
        address1.setZipCode("21654");

        Role role4 = new Role();
        role4.setId(1L);
        role4.setRoleType("Role Type");

        Customer customer4 = new Customer();
        customer4.setAddresses(new ArrayList<>());
        customer4.setCreditCard(new CreditCard("Card Name", "42", "Card Expiration", "Card Cvv", "127.0.0.1"));
        customer4.setCustomerStatus(CustomerStatus.ACTIVE);
        customer4.setDateOfRegistration(LocalDate.ofEpochDay(1L));
        customer4.setEmail("jane.doe@example.org");
        customer4.setFirstName("Jane");
        customer4.setId(1L);
        customer4.setLastName("Doe");
        customer4.setPassword("iloveyou");
        customer4.setPhone("6625550144");
        customer4.setRole(role4);

        Order order1 = new Order();
        order1.setAddress(address1);
        order1.setCustomer(customer4);
        order1.setId(1L);
        order1.setOrderDate("2020-03-01");
        order1.setOrderNumber(1L);
        order1.setOrderStatus(OrderStatus.SUCCESS);
        order1.setOrderTime("Order Time");
        order1.setOrderTotal(10.0d);
        order1.setProductCartItems(new ArrayList<>());
        when(orderRepo.save((Order) any())).thenReturn(order1);
        assertThrows(ResourceException.class, () -> orderServiceImpl.placeOrder(1L, new OrderDto()));
        verify(customerRepo).findById((Long) any());
        verify(customer).setAddresses((List<Address>) any());
        verify(customer).setCreditCard((CreditCard) any());
        verify(customer).setCustomerStatus((CustomerStatus) any());
        verify(customer).setDateOfRegistration((LocalDate) any());
        verify(customer).setEmail((String) any());
        verify(customer).setFirstName((String) any());
        verify(customer).setId((Long) any());
        verify(customer).setLastName((String) any());
        verify(customer).setPassword((String) any());
        verify(customer).setPhone((String) any());
        verify(customer).setRole((Role) any());
        verify(role).setId((Long) any());
        verify(role).setRoleType((String) any());
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
    }
}

