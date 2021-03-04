package com.example.sprotte.datahandling.product.service;

import com.example.sprotte.constants.ResponseMessageConstants;
import com.example.sprotte.datahandling.product.repository.ContentRatioRepository;
import com.example.sprotte.entity.barcontainerContentratio.ContentRatioProductBarContainer;
import com.example.sprotte.errorhandling.product.ContentRatioRelationNotFoundException;
import com.example.sprotte.errorhandling.product.IllegalContentRatioException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class ContentRatioServiceImplTest {

    @Mock
    ContentRatioRepository contentRatioRepository;

    @InjectMocks
    ContentRatioServiceImpl service;

    @Test
    void saveContentRatio() {
        ContentRatioProductBarContainer contentRatio = new ContentRatioProductBarContainer();
        given(contentRatioRepository.save(contentRatio)).willReturn(contentRatio);

        ContentRatioProductBarContainer savedContentRatio = service.saveContentRatio(contentRatio);

        assertThat(savedContentRatio).isNotNull();
        then(contentRatioRepository).should().save(any(ContentRatioProductBarContainer.class));
    }

    @Test
    void saveContentRatioThrow() {
        ContentRatioProductBarContainer contentRatio = new ContentRatioProductBarContainer();
        doThrow(new IllegalContentRatioException(ResponseMessageConstants.CONTENT_RATIO_ALREADY_EXIST)).when(contentRatioRepository).save(contentRatio);

        assertThrows(IllegalContentRatioException.class, () -> service.saveContentRatio(contentRatio));

        then(contentRatioRepository).should().save(any(ContentRatioProductBarContainer.class));
    }

    @Test
    void findContentRatioByProductIdAndBarContainerId() {
        Long productId = 1L;
        Long barContainer = 1L;
        ContentRatioProductBarContainer contentRatio = new ContentRatioProductBarContainer();
        given(contentRatioRepository.findByProductIdAndBarContainerId(productId, barContainer)).willReturn(contentRatio);

        ContentRatioProductBarContainer foundContentRatio = service.findContentRatioByProductIdAndBarContainerId(productId, barContainer);

        assertThat(foundContentRatio).isNotNull();
        then(contentRatioRepository).should().findByProductIdAndBarContainerId(anyLong(), anyLong());
    }

    @Test
    void findContentRatioByProductIdAndBarContainerIdThrow() {
        Long productId = 1L;
        Long barContainerId = 1L;
        given(contentRatioRepository.findByProductIdAndBarContainerId(productId, barContainerId)).willThrow(new ContentRatioRelationNotFoundException(ResponseMessageConstants.CONTENT_RATIO_NOT_FOUND));

        assertThrows(ContentRatioRelationNotFoundException.class, () -> service.findContentRatioByProductIdAndBarContainerId(productId, barContainerId));

        then(contentRatioRepository).should().findByProductIdAndBarContainerId(anyLong(), anyLong());
    }

    @Test
    void deleteContentRatioByProductIdAndBarContainerId() {
        Long productId = 1L;
        Long barContainerId = 1L;

        service.deleteContentRatioByProductIdAndBarContainerId(productId, barContainerId);

        then(contentRatioRepository).should().deleteByProductIdAndBarContainerId(anyLong(), anyLong());
    }

    @Test
    void deleteContentRatioByProductIdAndBarContainerIdThrow() {
        Long productId = 1L;
        Long barContainerId = 1L;
        willThrow(new RuntimeException("boom")).given(contentRatioRepository).deleteByProductIdAndBarContainerId(productId, barContainerId);

        assertThrows(RuntimeException.class, () -> service.deleteContentRatioByProductIdAndBarContainerId(productId, barContainerId));

        then(contentRatioRepository).should().deleteByProductIdAndBarContainerId(anyLong(), anyLong());
    }
}