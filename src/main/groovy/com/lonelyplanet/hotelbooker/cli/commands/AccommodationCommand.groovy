package com.lonelyplanet.hotelbooker.cli.commands

import com.beust.jcommander.Parameters
import com.beust.jcommander.Parameter
import com.lonelyplanet.hotelbooker.Application
import com.lonelyplanet.hotelbooker.cli.Command
import com.lonelyplanet.hotelbooker.model.Accommodation
import com.lonelyplanet.hotelbooker.model.AccommodationAndTravellers
import com.lonelyplanet.hotelbooker.model.Traveller

/**
 *
 */
@Parameters(commandDescription = "Display booking information by accommodation id")
class AccommodationCommand implements Command {

    final String name = "accommodation"

    @Parameter(names = "-accommodation", description = "Source of Accommodation data in JSON format")
    String accommodationFile

    @Parameter(names = "-traveller", description = "Source of Traveller and Criteria data in JSON format")
    String travellerFile

    @Parameter(names = "-id", description = "Id of the accommodation to lookup bookings for")
    long id

    void execute(PrintWriter out, Application application) {
        AccommodationAndTravellers accommodationAndTravellers = application.getBookingsByAccommodationId(id)
        out.println("Accommodation: ${accommodationAndTravellers.accommodation.name}")
        out.println("-----------------------------------------------")
        accommodationAndTravellers.travellers.each { out.println(it.name) }
    }

}
