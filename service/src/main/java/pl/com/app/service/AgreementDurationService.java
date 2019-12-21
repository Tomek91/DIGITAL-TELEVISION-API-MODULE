package pl.com.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.app.dto.AgreementDurationDTO;
import pl.com.app.dto.TvPackageDTO;
import pl.com.app.exceptions.ExceptionCode;
import pl.com.app.exceptions.MyException;
import pl.com.app.mappers.AgreementDurationMapper;
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
public class AgreementDurationService {
    private final AgreementDurationRepository agreementDurationRepository;
    private final TvPackageRepository tvPackageRepository;
    private final AgreementDurationMapper agreementDurationMapper;


    public List<AgreementDurationDTO> findAll() {
        try {
            return agreementDurationRepository
                    .findAll()
                    .stream()
                    .map(agreementDurationMapper::agreementDurationToAgreementDurationDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, e.getMessage());
        }
    }

    public AgreementDurationDTO findOne(Long id) {
        try {
            if (id == null) {
                throw new NullPointerException("AGREEMENT DURATION ID IS NULL");
            }

            return agreementDurationRepository
                    .findById(id)
                    .map(agreementDurationMapper::agreementDurationToAgreementDurationDTO)
                    .orElseThrow(NullPointerException::new);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, e.getMessage());
        }
    }

    public AgreementDurationDTO add(AgreementDurationDTO agreementDurationDTO) {

        try {
            if (agreementDurationDTO == null) {
                throw new NullPointerException("AGREEMENT DURATION IS NULL");
            }
            if (agreementDurationDTO.getTvPackagesDTO() == null) {
                throw new NullPointerException("TV PACKAGES IS NULL");
            }

            Set<TvPackage> tvPackageList = agreementDurationDTO
                    .getTvPackagesDTO()
                    .stream()
                    .map(TvPackageDTO::getName)
                    .map(x -> tvPackageRepository.findByName(x).orElseThrow(NullPointerException::new))
                    .collect(Collectors.toSet());


            AgreementDuration agreementDurationToSave = agreementDurationMapper.agreementDurationDTOToAgreementDuration(agreementDurationDTO);
            agreementDurationToSave.setTvPackages(tvPackageList);

            AgreementDuration agreementDurationFromDb = agreementDurationRepository.save(agreementDurationToSave);
            return agreementDurationMapper.agreementDurationToAgreementDurationDTO(agreementDurationFromDb);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, e.getMessage());
        }
    }

    public AgreementDurationDTO update(Long id, AgreementDurationDTO agreementDurationDTO) {
        try {
            if (id == null) {
                throw new NullPointerException("ID IS NULL");
            }
            if (agreementDurationDTO == null) {
                throw new NullPointerException("AGREEMENT DURATION IS NULL");
            }

            AgreementDuration agreementDuration = agreementDurationRepository
                    .findById(id).orElseThrow(NullPointerException::new);

            agreementDuration.setDuration(agreementDurationDTO.getDuration() == null ? agreementDuration.getDuration() : agreementDurationDTO.getDuration());

            AgreementDuration agreementDurationAfterUpdate = agreementDurationRepository
                    .save(agreementDuration);
            return agreementDurationMapper.agreementDurationToAgreementDurationDTO(agreementDurationAfterUpdate);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, e.getMessage());
        }
    }

    public AgreementDurationDTO delete(Long id) {

        try {
            if (id == null) {
                throw new NullPointerException("AGREEMENT DURATION ID IS NULL");
            }

            AgreementDuration agreementDuration = agreementDurationRepository
                    .findById(id)
                    .orElseThrow(NullPointerException::new);
            agreementDurationRepository.delete(agreementDuration);
            return agreementDurationMapper.agreementDurationToAgreementDurationDTO(agreementDuration);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, e.getMessage());
        }
    }
}
