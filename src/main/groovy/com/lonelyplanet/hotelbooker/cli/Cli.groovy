package com.lonelyplanet.hotelbooker.cli

import com.beust.jcommander.JCommander
import com.beust.jcommander.MissingCommandException
import com.lonelyplanet.hotelbooker.Application
import org.apache.commons.lang.StringUtils
import com.lonelyplanet.hotelbooker.cli.commands.UnknownCommand
import com.lonelyplanet.hotelbooker.cli.commands.TravellerCommand
import com.lonelyplanet.hotelbooker.cli.commands.SearchCommand
import com.lonelyplanet.hotelbooker.cli.commands.AccommodationCommand

/**
 *
 */
class Cli {

    public static void main(String... args) {
        new Cli(new PrintWriter(new BufferedOutputStream(System.out))).parse(args)
    }

    protected List<Command> COMMAND_LIST = Arrays.asList(
            new AccommodationCommand(),
            new TravellerCommand(),
            new SearchCommand(),
    )

    protected Application application

    protected Map<String, Command> commands
    protected UnknownCommand unknownCommand
    protected JCommander commander
    protected PrintWriter out

    Cli(PrintWriter writer) {
        application = new Application()
        out = writer
        unknownCommand = new UnknownCommand()
        commands = [:]
        COMMAND_LIST.each { Command c -> commands.put(c.name, c) }

        commander = new JCommander()
        commands.each{ commander.addCommand(it.key, it.value) }
    }

    public void parse(String... args) {
        try {
            commander.parse(args)
            Command command = commands.get(commander.getParsedCommand())
            if(command == null) {
                commander.usage()
                return
            }
            loadDataFrom(command)
            command.execute(out, application)
            out.flush()
        }
        catch (MissingCommandException e) {
            unknownCommand.execute(out, application)
        }
    }

    void loadDataFrom(Command command) {
        if(StringUtils.isBlank(command.accommodationFile)) {
            throw new NullPointerException("--accommodation-file must be a non-null path")
        }
        if(StringUtils.isBlank(command.travellerFile)) {
            throw new NullPointerException("--traveller-file must be a non-null path")
        }
        File accommodation= new File(command.accommodationFile)
        File travellers = new File(command.travellerFile)
        if(!accommodation.exists() || accommodation.isDirectory()) {
            throw new IllegalArgumentException("--accommodation-file value does not exist or is not a file")
        }
        if(!travellers.exists() || travellers.isDirectory()) {
            throw new IllegalArgumentException("--travellers-file value does not exist or is not a file")
        }

        application.loadAccommodations(accommodation)
        application.autobookTravellers(travellers)
    }

}
