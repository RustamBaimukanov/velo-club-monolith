package com.work.veloclub.model.event_profile;

import com.work.veloclub.model.event.Event;
import com.work.veloclub.model.user_profile.UserProfile;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "events_user_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventUserProfile {

    @Id
    @ManyToOne
    private Event event;

    @Id
    @ManyToOne
    private UserProfile userProfile;
}
