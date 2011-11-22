package com.lonelyplanet.hotelbooker.cli.commands

import com.beust.jcommander.Parameters
import com.beust.jcommander.Parameter
import com.lonelyplanet.hotelbooker.Application
import com.lonelyplanet.hotelbooker.cli.Command
import com.lonelyplanet.hotelbooker.model.AccommodationAndTravellers

/**
 *
 */
@Parameters(commandDescription = "Display booking information by traveller id")
class TravellerCommand implements Command {

    final String name = "traveller"

    @Parameter(names = "-accommodation", description = "Source of Accommodation data in JSON format")
    String accommodationFile

    @Parameter(names = "-traveller", description = "Source of Traveller and Criteria data in JSON format")
    String travellerFile

    @Parameter(names = "-id", description = "Id of the traveller to display bookings for")
    long id

    void execute(PrintWriter out, Application application) {
        AccommodationAndTravellers accommodationAndTravellers = application.getBookingsByTravellerId(id)
        out.println("Traveller: ${accommodationAndTravellers.travellers.get(0)?.name}")
        out.println("Booked at: ${accommodationAndTravellers.accommodation.name}")
    }

}
