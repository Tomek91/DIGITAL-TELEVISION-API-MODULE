package pl.com.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.com.app.dto.CategoryDTO;
import pl.com.app.dto.ChannelDTO;
import pl.com.app.exceptions.ExceptionCode;
import pl.com.app.exceptions.MyException;
import pl.com.app.mappers.CategoryMapper;
import pl.com.app.mappers.ChannelMapper;
import pl.com.app.parsers.json.CategoriesConverter;
import pl.com.app.parsers.json.ChannelsConverter;
import pl.com.app.repository.CategoryRepository;
import pl.com.app.repository.ChannelRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
//@Transactional
@RequiredArgsConstructor
public class DataInitializerService {
    private final ChannelRepository channelRepository;
    private final CategoryRepository categoryRepository;
    private final ChannelsConverter channelsConverter;
    private final CategoriesConverter categoriesConverter;
    private final CategoryMapper categoryMapper;
    private final ChannelMapper channelMapper;


    public void initData() {
        try {
            channelRepository.deleteAll();
            categoryRepository.deleteAll();


            List<ChannelDTO> channelDTOList = channelsConverter.fromJson().orElseThrow(NullPointerException::new);
            List<CategoryDTO> categoryDTOList = categoriesConverter.fromJson().orElseThrow(NullPointerException::new);

            categoryRepository.saveAll(
                    categoryDTOList
                            .stream()
                            .map(categoryMapper::categoryDTOToCategory)
                            .collect(Collectors.toList())
            );


            channelRepository.saveAll(
                    channelDTOList
                            .stream()
                            .map(channelMapper::channelDTOToChannel)
                            .peek(x -> x.setCategory(categoryRepository.findByName(x.getCategory().getName()).orElseThrow(() -> new NullPointerException("DATA INITIALIZER: FIND CATEGORY BY NAME EXCEPTION " + x.getName()))))
                            .collect(Collectors.toList())
            );


        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(ExceptionCode.SERVICE, e.getMessage());
        }
    }
}
