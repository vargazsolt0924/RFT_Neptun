package hu.nye.rft.neptun.model.forms;

import lombok.Getter;

@Getter
public class SubjectForm {
    private String dayOfTheWeek;
    private int durationInMinutes;
    private int credit;
    private int maxHallgato;
    private String name;
    private String startTime;

    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public void setcredit(int credit) {
        this.credit = credit;
    }

    public void setMaxHallgato(int maxHallgato) {
        this.maxHallgato = maxHallgato;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
