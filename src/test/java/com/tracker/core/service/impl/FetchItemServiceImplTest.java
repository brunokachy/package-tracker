package com.tracker.core.service.impl;

import com.tracker.core.service.RegisterItemService;
import com.tracker.infrastructure.persistence.service.ItemPersistenceService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FetchItemServiceImplTest {

    @Mock
    ItemPersistenceService itemPersistenceService;

    @Mock
    RegisterItemService registerItemService;

    @InjectMocks
    FetchItemServiceImpl fetchItemService;

}
