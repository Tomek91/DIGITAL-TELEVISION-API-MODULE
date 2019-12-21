package pl.com.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.app.dto.ChannelDTO;
import pl.com.app.exceptions.ExceptionCode;
import pl.com.app.exceptions.MyException;
import pl.com.app.mappers.ChannelMapper;
import pl.com.app.model.Category;
import pl.com.app.model.Channel;
import pl.com.app.repository.CategoryRepository;
import pl.com.app.repository.ChannelRepository;
import pl.com.app.repository.TvPackageRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ChannelService {
    private final ChannelRepository channelRepository;
    private final ChannelMapper channelMapper;
    private final CategoryRepository categoryRepository;
    private final TvPackageRepository tvPackageRepository;


    public List<ChannelDTO> findAll() {
        try {
            return channelRepository
                    .findAll()
                    .stream()
                    .map(channelMapper::channelToChannelDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, e.getMessage());
        }
    }

    public ChannelDTO findOne(Long id) {
        try {
            if (id == null) {
                throw new NullPointerException("CHANNEL ID IS NULL");
            }

            return channelRepository
                    .findById(id)
                    .map(channelMapper::channelToChannelDTO)
                    .orElseThrow(NullPointerException::new);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, e.getMessage());
        }
    }

    public ChannelDTO add(ChannelDTO channelDTO) {

        try {
            if (channelDTO == null) {
                throw new NullPointerException("CHANNEL IS NULL");
            }
            if (channelDTO.getCategoryDTO() == null) {
                throw new NullPointerException("CATEGORY IS NULL");
            }
            if (channelDTO.getCategoryDTO().getId() == null) {
                throw new NullPointerException("CATEGORY IS NULL");
            }

            Channel channel = channelMapper.channelDTOToChannel(channelDTO);
            Category category = categoryRepository.findById(channelDTO.getCategoryDTO().getId()).orElseThrow(NullPointerException::new);
            channel.setCategory(category);

            Channel channelFromDb = channelRepository.save(channel);
            return channelMapper.channelToChannelDTO(channelFromDb);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, e.getMessage());
        }
    }

    public ChannelDTO update(Long id, ChannelDTO channelDTO) {
        try {
            if (channelDTO == null) {
                throw new NullPointerException("CHANNEL IS NULL");
            }
            if (channelDTO.getCategoryDTO() == null) {
                throw new NullPointerException("CATEGORY IS NULL");
            }
            if (channelDTO.getCategoryDTO().getId() == null) {
                throw new NullPointerException("CATEGORY IS NULL");
            }

            Channel channel = channelRepository.findById(id).orElseThrow(NullPointerException::new);

            Category category = categoryRepository.findById(channelDTO.getCategoryDTO().getId()).orElseThrow(NullPointerException::new);
            channel.setCategory(category);
            channel.setName(channelDTO.getName() == null ? channel.getName() : channelDTO.getName());
            channel.setPackageName(channelDTO.getPackageName() == null ? channel.getPackageName() : channelDTO.getPackageName());

            Channel channelAfterUpdate = channelRepository.save(channel);
            return channelMapper.channelToChannelDTO(channelAfterUpdate);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, e.getMessage());
        }
    }

    public ChannelDTO delete(Long id) {

        try {
            if (id == null) {
                throw new NullPointerException("CHANNEL ID IS NULL");
            }
            Channel channelToDelete = channelRepository.findById(id)
                    .orElseThrow(NullPointerException::new);
            channelRepository.delete(channelToDelete);
            return channelMapper.channelToChannelDTO(channelToDelete);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, e.getMessage());
        }
    }
}
