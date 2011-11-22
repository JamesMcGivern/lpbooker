package com.lonelyplanet.hotelbooker.cli.commands

import com.beust.jcommander.Parameters
import com.lonelyplanet.hotelbooker.Application
import com.beust.jcommander.Parameter
import com.lonelyplanet.hotelbooker.cli.Command
import com.beust.jcommander.converters.CommaSeparatedConverter
import com.lonelyplanet.hotelbooker.model.Criteria
import com.lonelyplanet.hotelbooker.model.Accommodation

/**
 *
 */
@Parameters(commandDescription = "Search for best matching accommodation from criteria")
class SearchCommand implements Command {

    final String name = "search"

    @Parameter(names = "-accommodation", description = "Source of Accommodation data in JSON format")
    String accommodationFile

    @Parameter(names = "-traveller", description = "Source of Traveller and Criteria data in JSON format")
    String travellerFile

    @Parameter(names = "-price", arity = 2, description = "Price range upper and lower bounds")
    List<Integer> price

    @Parameter(names = "-attributes", converter = CommaSeparatedConverter.class, description = "Price range upper and lower bounds")
    List<String> attributes

    void execute(PrintWriter out, Application application) {
        Criteria criteria = new Criteria().priceRange(new IntRange(Integer.parseInt(price.get(0)), Integer.parseInt(price.get(1))))
                                                              .attributes(attributes.collect {it.replace('"', '')})
        Accommodation accommodation = application.findAccommodation(criteria)
        out.println("${accommodation.name} (${accommodation.price})")
    }

}
