package bg.softuni.mobilele.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ActivationTokenCleanUpScheduler {

  // TODO
  @Scheduled(cron = "0 5 * * * ?")
  public void cleanUpTokens() {
    //TODO - call the user service...
  }

}
