package com.lonelyplanet.hotelbooker.cli.commands

import com.lonelyplanet.hotelbooker.Application
import com.lonelyplanet.hotelbooker.cli.Command

/**
 *
 */
class UnknownCommand implements Command {

    final String name = null

    void execute(PrintWriter out, Application application) {
        out.println("Unknown command")
    }

    String getAccommodationFile() {
        throw new UnsupportedOperationException("Invalid Operation on Unknown Command")
    }

    String getTravellerFile() {
        throw new UnsupportedOperationException("Invalid Operation on Unknown Command")
    }
}
