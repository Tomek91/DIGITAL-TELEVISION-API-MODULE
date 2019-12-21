package pl.com.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.com.app.parsers.FileNames;
import pl.com.app.parsers.json.*;

@Configuration
@ComponentScan("pl.com.app")
public class ConverterConfig {

    @Bean
    public CategoriesConverter categoriesConverter() {
        return new CategoriesConverter(FileNames.CATEGORIES);
    }

    @Bean
    public ChannelsConverter channelsConverter() {
        return new ChannelsConverter(FileNames.CHANNELS);
    }

    @Bean
    public Channels2Converter channels2Converter() {
        return new Channels2Converter(FileNames.CHANNELS2);
    }

    @Bean
    public AgreementDurationConverter agreementDurationConverter() {
        return new AgreementDurationConverter(FileNames.DURATION_AGREEMENT);
    }

    @Bean
    public TvPackagesConverter tvPackagesConverter() {
        return new TvPackagesConverter(FileNames.TV_PACKAGES);
    }


}
