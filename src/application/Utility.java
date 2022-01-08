package application;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.Period;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

public class Utility {

	public static void hackTooltipStartTiming(Tooltip tooltip) {
		try {
			Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
			fieldBehavior.setAccessible(true);
			Object objBehavior = fieldBehavior.get(tooltip);

			Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
			fieldTimer.setAccessible(true);
			Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

			objTimer.getKeyFrames().clear();
			objTimer.getKeyFrames().add(new KeyFrame(new Duration(250)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getAgeFromDate(LocalDate birthday) {
		LocalDate today = LocalDate.now();

		Period period = Period.between(birthday, today);

		return period.getYears();
	}
}
