package pl.com.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.app.dto.AgreementDurationDTO;
import pl.com.app.dto.TvPackageDTO;
import pl.com.app.exceptions.ExceptionCode;
import pl.com.app.exceptions.MyException;
import pl.com.app.mappers.TvPackageMapper;
import pl.com.app.model.AgreementDuration;
import pl.com.app.model.TvPackage;
import pl.com.app.repository.AgreementDurationRepository;
import pl.com.app.repository.TvPackageRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TvPackageService {
    private final AgreementDurationRepository agreementDurationRepository;
    private final TvPackageRepository tvPackageRepository;
    private final TvPackageMapper tvPackageMapper;


    public List<TvPackageDTO> findAll() {
        try {
            return tvPackageRepository
                    .findAll()
                    .stream()
                    .map(tvPackageMapper::tvPackageToTvPackageDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, e.getMessage());
        }
    }

    public TvPackageDTO findOne(Long id) {
        try {
            if (id == null) {
                throw new NullPointerException("TV PACKAGE ID IS NULL");
            }

            return tvPackageRepository
                    .findById(id)
                    .map(tvPackageMapper::tvPackageToTvPackageDTO)
                    .orElseThrow(NullPointerException::new);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, e.getMessage());
        }
    }

    public TvPackageDTO add(TvPackageDTO tvPackageDTO) {

        try {
            if (tvPackageDTO == null) {
                throw new NullPointerException("TV PACKAGE IS NULL");
            }
            if (tvPackageDTO.getAgreementDurationsDTO() == null) {
                throw new NullPointerException("AGREEMENT DURATION IS NULL");
            }

            Set<AgreementDuration> agreementDurationSet = tvPackageDTO
                    .getAgreementDurationsDTO()
                    .stream()
                    .map(AgreementDurationDTO::getDuration)
                    .map(x -> agreementDurationRepository.findByDuration(x).orElseThrow(NullPointerException::new))
                    .collect(Collectors.toSet());


            TvPackage tvPackageToSave = tvPackageMapper.tvPackageDTOToTvPackage(tvPackageDTO);
            tvPackageToSave.setAgreementDurations(agreementDurationSet);

            TvPackage tvPackageFromDb = tvPackageRepository.save(tvPackageToSave);
            return tvPackageMapper.tvPackageToTvPackageDTO(tvPackageFromDb);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, e.getMessage());
        }
    }

    public TvPackageDTO update(Long id, TvPackageDTO tvPackageDTO) {
        try {
            if (id == null) {
                throw new NullPointerException("ID IS NULL");
            }
            if (tvPackageDTO == null) {
                throw new NullPointerException("TV PACKAGE IS NULL");
            }

            TvPackage tvPackage = tvPackageRepository
                    .findById(id).orElseThrow(NullPointerException::new);

            tvPackage.setCost(tvPackageDTO.getCost() == null ? tvPackage.getCost() : tvPackageDTO.getCost());
            tvPackage.setName(tvPackageDTO.getName() == null ? tvPackage.getName() : tvPackageDTO.getName());

            TvPackage tvPackageAfterUpdate = tvPackageRepository.save(tvPackage);
            return tvPackageMapper.tvPackageToTvPackageDTO(tvPackageAfterUpdate);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, e.getMessage());
        }
    }

    public TvPackageDTO delete(Long id) {

        try {
            if (id == null) {
                throw new NullPointerException("TV PACKAGE ID IS NULL");
            }

            TvPackage tvPackage = tvPackageRepository
                    .findById(id)
                    .orElseThrow(NullPointerException::new);
            tvPackageRepository.delete(tvPackage);
            return tvPackageMapper.tvPackageToTvPackageDTO(tvPackage);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, e.getMessage());
        }
    }
}
