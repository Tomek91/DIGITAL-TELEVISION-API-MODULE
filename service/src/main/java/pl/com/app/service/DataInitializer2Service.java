package pl.com.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.com.app.exceptions.ExceptionCode;
import pl.com.app.exceptions.MyException;
import pl.com.app.mappers.AgreementDurationMapper;
import pl.com.app.mappers.CategoryMapper;
import pl.com.app.mappers.ChannelMapper;
import pl.com.app.mappers.TvPackageMapper;
import pl.com.app.parsers.json.AgreementDurationConverter;
import pl.com.app.parsers.json.CategoriesConverter;
import pl.com.app.parsers.json.ChannelsConverter;
import pl.com.app.parsers.json.TvPackagesConverter;
import pl.com.app.repository.AgreementDurationRepository;
import pl.com.app.repository.CategoryRepository;
import pl.com.app.repository.ChannelRepository;
import pl.com.app.repository.TvPackageRepository;

@Service
@Slf4j
//@Transactional
@RequiredArgsConstructor
public class DataInitializer2Service {
    private final AgreementDurationRepository agreementDurationRepository;
    private final ChannelRepository channelRepository;
    private final CategoryRepository categoryRepository;
    private final TvPackageRepository tvPackageRepository;
    private final AgreementDurationConverter agreementDurationConverter;
    private final ChannelsConverter channelsConverter;
    private final CategoriesConverter categoriesConverter;
    private final TvPackagesConverter packagesConverter;
    private final CategoryMapper categoryMapper;
    private final TvPackageMapper tvPackageMapper;
    private final ChannelMapper channelMapper;
    private final AgreementDurationMapper agreementDurationMapper;


    public void initData() {
        try {
//            channelRepository.deleteAll();
//            categoryRepository.deleteAll();
//            agreementDurationRepository.deleteAll();
//            tvPackageRepository.deleteAll();
//
//
//            List<ChannelDTO> channelDTOList = channelsConverter.fromJson().orElseThrow(NullPointerException::new);
//            List<CategoryDTO> categoryDTOList = categoriesConverter.fromJson().orElseThrow(NullPointerException::new);
//            List<AgreementDurationDTO> agreementDurationDTOList = agreementDurationConverter.fromJson().orElseThrow(NullPointerException::new);
//            List<TvPackageDTO> tvPackageDTOList = packagesConverter.fromJson().orElseThrow(NullPointerException::new);
//
//            categoryRepository.saveAll(
//                    categoryDTOList
//                            .stream()
//                            .map(categoryMapper::categoryDTOToCategory)
//                            .collect(Collectors.toList())
//            );
//
//            agreementDurationRepository.saveAll(
//                    agreementDurationDTOList
//                            .stream()
//                            .map(agreementDurationMapper::agreementDurationDTOToAgreementDuration)
//                            .collect(Collectors.toList())
//            );
//
//            tvPackageRepository.saveAll(
//                    tvPackageDTOList
//                            .stream()
//                            .map(tvPackageMapper::tvPackageDTOToTvPackage)
//                            .peek(x -> x.setAgreementDurations(
//                                    x.getAgreementDurations()
//                                            .stream()
//
//                                            .map(a -> agreementDurationRepository.findByDuration(a.getDuration()).orElseThrow(() -> new NullPointerException("DATA INITIALIZER: FIND AGREEMENT BY DURATION EXCEPTION")))
//                                            .collect(Collectors.toSet()))
//                            )
//                            .collect(Collectors.toList())
//            );
//
//            channelRepository.saveAll(
//                    channelDTOList
//                            .stream()
//                            .map(channelMapper::channelDTOToChannel)
//                            .peek(x -> x.setCategory(categoryRepository.findByName(x.getCategory().getName()).orElseThrow(() -> new NullPointerException("DATA INITIALIZER: FIND CATEGORY BY NAME EXCEPTION " + x.getName()))))
//                            .peek(x -> x.setTvPackage(tvPackageRepository.findByName(x.getTvPackage().getName()).orElseThrow(() -> new NullPointerException("DATA INITIALIZER: FIND TV PACKAGE BY NAME EXCEPTION"))))
//                            .collect(Collectors.toList())
//            );


        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(ExceptionCode.SERVICE, e.getMessage());
        }
    }
}
