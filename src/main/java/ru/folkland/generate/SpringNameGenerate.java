package ru.folkland.generate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import ru.folkland.constants.Constants;

import java.util.List;

@Configuration
public class SpringNameGenerate {

    @Value("{${naming.first}")
    private List<String> firstName;

    @Value("${naming.last}")
    private List<String> lastName;

    @Value("${naming.club}")
    private List<String> clubName;

    String getFirstName() {
        return firstName.get(Constants.RANDOM.nextInt(firstName.size()));
    }

    String getLastName() {
        return lastName.get(Constants.RANDOM.nextInt(lastName.size()));
    }

    String clubName() {
        String naming = clubName.get(Constants.RANDOM.nextInt(clubName.size()));
        clubName.remove(naming);
        return naming;
    }
}
