package pl.com.app.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tv_packages")
public class TvPackage {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal cost;

    @OneToMany(mappedBy = "tvPackage", fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Channel2> channels;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "packages_durations",
            joinColumns = @JoinColumn(name = "package_id"),
            inverseJoinColumns = @JoinColumn(name = "duration_id")
    )
    private Set<AgreementDuration> agreementDurations;
}





