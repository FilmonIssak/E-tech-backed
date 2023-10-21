package com.Etech.Service.Impl;


import com.Etech.Dto.CartDto;
import com.Etech.Dto.ProductDto;
import com.Etech.Dto.ViewerDto;
import com.Etech.Exception.ResourceException;
import com.Etech.Model.Cart;
import com.Etech.Model.Product;
import com.Etech.Model.Viewer;
import com.Etech.Model.enums.ProductCategory;
import com.Etech.Repository.CartRepo;
import com.Etech.Repository.ProductRepo;
import com.Etech.Repository.ViewerRepo;
import com.Etech.Service.ViewerService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ViewerServiceImp implements ViewerService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ViewerRepo viewerRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ProductDto findProductByName(String name) {
        Product product = productRepo.getProductByName (name).orElseThrow ( () -> new ResourceException("Product with the Name : "+ name + " is not present"));
        return modelMapper.map (  product,ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepo.findAll ().stream ().map ( product -> modelMapper.map ( product, ProductDto.class)).collect( Collectors.toList());
    }

    @Override
    public List<ProductDto> findAllByCategory(String category) throws RuntimeException {
        ProductCategory productCategory;
        try {
             productCategory = ProductCategory.valueOf(category.toUpperCase());

        }
        catch (IllegalArgumentException e){
            throw  new ResourceException  ("No category with the name :" + category);
        }

        List<Product> product =productRepo.findProductsByProductCategory(productCategory);

        if(product.isEmpty ()) {
            throw new ResourceException ("The Category: " + category + " has no Products");
        }
        else
        return product.stream ().map ( p -> modelMapper.map ( p, ProductDto.class ) ).collect ( Collectors.toList () );
    }

    @Override
      public List<ProductDto> findAllByKeyWord(String keyWord) {
          return productRepo.findAllByKeyWord ( keyWord ).stream ().map ( product -> modelMapper.map ( product, ProductDto.class)).collect( Collectors.toList());
    }


//    @Override
//    public List<ProductDto> filterProducts(String name, String category, String keyWord) {
//
//        List<ProductDto> productDtoList = new ArrayList<> ();
//        if(keyWord!=null){
//            productDtoList = productRepo.findAllByKeyWord ( keyWord ).stream ().map ( product -> modelMapper.map ( product, ProductDto.class)).collect( Collectors.toList());
//        }
//        if(category!=null){
//            ProductCategory productCategory;
//
//            try {
//                productCategory = ProductCategory.valueOf(category.toUpperCase());
//
//            }
//            catch (IllegalArgumentException e){
//                if(name!=null){
//                    Product product = productRepo.getProductByName (name).orElseThrow ( () -> new ResourceException("Product with the Name : "+ name + " is not present"));
//                    productDtoList.add ( modelMapper.map ( product, ProductDto.class));
//                }
//
//             List<Product> product =productRepo.findProductsByProductCategory(productCategory);
//
//            if(product.isEmpty ()) {
//                throw new ResourceException ("The Category: " + category + " has no Products");
//            }
//            else
//                return product.stream ().map ( p -> modelMapper.map ( p, ProductDto.class ) ).collect ( Collectors.toList () );
//
//
//        }
//
//        if(name!=null){
//            Product product = productRepo.getProductByName (name).orElseThrow ( () -> new ResourceException("Product with the Name : "+ name + " is not present"));
//            productDtoList.add ( modelMapper.map ( product, ProductDto.class));
//        }
//        return productDtoList;
//     }

    public ViewerDto register(ViewerDto viewerDto) {
        Viewer viewer = modelMapper.map(viewerDto, Viewer.class);
        Viewer savedViewer = viewerRepo.save(viewer);
        return modelMapper.map(savedViewer, ViewerDto.class);
    }

    @Override
    public CartDto addProductToViewerCart(Long viewerId, Long productId, int quantity) {
        if (viewerId == null || productId == null) {
            throw new IllegalArgumentException("ViewerId or ProductId cannot be null!");
        }
        Viewer viewer = viewerRepo.findById(viewerId)
                .orElseThrow(() -> new ResourceNotFoundException("Viewer not found"));
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Cart viewerCart = viewer.getCart();
        if (viewerCart == null) {
            viewerCart = new Cart();
            viewer.setCart(viewerCart);
            viewerCart.setViewer(viewer);
        }

        if(quantity> product.getQuantity()){
            throw new ResourceException("Not Enough quantity we only have "  + product.getQuantity() + " - "+ product.getName() + " In our Stock" , HttpStatus.CONFLICT);
        }
        viewerCart.addProduct(product, quantity); // Adjusted to accommodate quantity

        viewerRepo.save(viewer);
        viewerCart = viewer.getCart(); // Refresh the viewerCart object after saving
        return modelMapper.map(viewerCart, CartDto.class);
    }

    @Override
    public CartDto deleteProductFromCartForViewer(Long viewerId, Long productId) {
        Viewer viewer = viewerRepo.findById(viewerId).orElseThrow(() -> new ResourceException("Viewer not found"));
        Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceException("Product not found"));
        Cart viewerCart = viewer.getCart();
        viewerCart.removeProduct(product);
        viewerCart.updateTotalPrice();
        cartRepo.save(viewerCart);
        return modelMapper.map(viewerCart, CartDto.class);
    }

}
