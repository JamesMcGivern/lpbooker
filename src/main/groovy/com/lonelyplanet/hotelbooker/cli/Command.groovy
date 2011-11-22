package com.lonelyplanet.hotelbooker.cli

import com.lonelyplanet.hotelbooker.Application

/**
 *
 */
public interface Command {

    String getName()
    void execute(PrintWriter printWriter, Application application)
    String getAccommodationFile()
    String getTravellerFile()

}
