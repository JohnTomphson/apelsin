package com.example.demoapelsin.service;

import com.example.demoapelsin.entity.Categorys;
import com.example.demoapelsin.payload.ApiResponse;
import com.example.demoapelsin.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    final CategoryRepository categoryRepository;


    public ApiResponse getAll() {
        List<Categorys> categorysList = categoryRepository.findAllByActiveTrue();
        return categorysList != null ? new ApiResponse(true, "Success", categorysList)
                : new ApiResponse(false, "Failed");
    }


    public ApiResponse getById(Integer id) {
        Optional<Categorys> categorysOptional = categoryRepository.findById(id);
        return categorysOptional.map(address -> new ApiResponse(true, "Success", address))
                .orElseGet(() -> new ApiResponse(false, "Not Found"));
    }


    public ApiResponse add(Categorys categorys) {
//        boolean existsByName = categoryRepository.existsByCategorys(categorys);
//        if (existsByName) {
//            return new ApiResponse(false, "This category already exsists");
//        }
        categoryRepository.save(categorys);
        return new ApiResponse(true, "Saved");
    }


    public ApiResponse update(Integer id, Categorys updateCategory) {
        Optional<Categorys> categorysOptional = categoryRepository.findById(id);
        if (!categorysOptional.isPresent()) {
            return new ApiResponse(false, "Not Found");
        }

        Categorys categorys = categorysOptional.get();
        categorys.setName(updateCategory.getName());
        categoryRepository.save(categorys);
        return new ApiResponse(true, "updated", categorys);
    }

    public ApiResponse delete(Integer id) {
        Optional<Categorys> categorysOptional = categoryRepository.findById(id);
        if (!categorysOptional.isPresent()) {
            return new ApiResponse(false, "Not Found");
        }
        Categorys categorys = categorysOptional.get();
        categorys.setActive(false);
        categoryRepository.save(categorys);
        return new ApiResponse(true, "deleted");
    }


}
