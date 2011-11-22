package com.lonelyplanet.hotelbooker.cli

import org.junit.Test
import org.junit.Before
import static junit.framework.Assert.assertEquals;

/**
 *
 */
public class CliTest {

    StringWriter out
    Cli cli

    @Before
    public void setup() {
        out = new StringWriter()
        cli = new Cli(new PrintWriter(out))
    }

    @Test
    public void shouldReturnUnknownCommandIfInputNotRecognised() {
        //when
        cli.parse("gibberish");

        //then
        StringWriter expectedOut = new StringWriter().append("Unknown command\n")
        assertEquals(expectedOut.toString(), out.toString())
    }

    @Test
    public void shouldDisplayTravellerBookingDetails() {
        //given
        String accommodationFile = new File(this.class.classLoader.getResource("accommodation.json").toURI()).absolutePath
        String travellerFile = new File(this.class.classLoader.getResource("traveller.json").toURI()).absolutePath

        //when
        cli.parse(["traveller",  "-accommodation",  "$accommodationFile",  "-traveller",  "$travellerFile",  "-id", "0"] as String[]);

        //then
        StringWriter expectedOut = new StringWriter()
        PrintWriter writer = new PrintWriter(expectedOut)
        writer.println("Traveller: Mrs. Tess Goyette")
        writer.println("Booked at: Cremin Apartments")

        assertEquals(expectedOut.toString(), out.toString())
    }

    @Test
    public void shouldDisplayAccommodationBookingDetails() {
        //given
        String accommodationFile = new File(this.class.classLoader.getResource("accommodation.json").toURI()).absolutePath
        String travellerFile = new File(this.class.classLoader.getResource("traveller.json").toURI()).absolutePath

        //when
        cli.parse(["accommodation",  "-accommodation",  "$accommodationFile",  "-traveller",  "$travellerFile",  "-id", "0"] as String[]);

        //then
        StringWriter expectedOut = new StringWriter()
        PrintWriter writer = new PrintWriter(expectedOut)
        writer.println("Accommodation: Cremin Apartments")
        writer.println("-----------------------------------------------")
        writer.println("Mrs. Tess Goyette")

        assertEquals(expectedOut.toString(), out.toString())
    }

    @Test
    public void shouldDisplayAccommodationDetailsOfSearch() {
        //given
        String accommodationFile = new File(this.class.classLoader.getResource("accommodation.json").toURI()).absolutePath
        String travellerFile = new File(this.class.classLoader.getResource("traveller.json").toURI()).absolutePath

        //when
        cli.parse(["search",
                         "-accommodation",  "$accommodationFile",
                         "-traveller",  "$travellerFile",
                         "-price", "0", "1000",
                         "-attributes", "gym,bath,24 hour reception"
        ] as String[]);

        //then
        StringWriter expectedOut = new StringWriter()
        PrintWriter writer = new PrintWriter(expectedOut)
        writer.println("Cremin Apartments (253)")

        assertEquals(expectedOut.toString(), out.toString())
    }
}
