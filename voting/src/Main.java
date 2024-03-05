import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Votes votes = new Votes(1000, 800, 150, 50);

        System.out.println("--------------------------Static value of exercise-------------------------");
        System.out.println("Percentage of valid votes in relation to the total number of voters: " +
                votes.percentageValidVotes(votes.getVotersTotal(), votes.getValidVotes()) + "%");

        System.out.println("Percentage of blank votes in relation to the total number of voters: " +
                votes.percentageBlankVotes(votes.getVotersTotal(), votes.getBlankVotes()) + "%");

        System.out.println("Percentage of null votes in relation to the total number of voters: " +
                votes.percentageNullVotes(votes.getVotersTotal(), votes.getNullVotes()) + "%\n");

        System.out.println("-----------------------------Generic solution------------------------------");
        //Generic solution
        Scanner scanner = new Scanner(System.in);

        System.out.print("Insert the total voters: ");
        votes.setVotersTotal(scanner.nextInt());

        System.out.print("Insert the total valid votes: ");
        votes.setValidVotes(scanner.nextInt());

        System.out.print("Insert the total blank votes: ");
        votes.setBlankVotes(scanner.nextInt());

        System.out.print("Insert the total null votes: ");
        votes.setNullVotes(scanner.nextInt());

        System.out.println();

        System.out.println("Percentage of valid votes in relation to the total number of voters: " +
                votes.percentageValidVotes(votes.getVotersTotal(), votes.getValidVotes()) + "%");

        System.out.println("Percentage of blank votes in relation to the total number of voters: " +
                votes.percentageBlankVotes(votes.getVotersTotal(), votes.getBlankVotes()) + "%");

        System.out.println("Percentage of null votes in relation to the total number of voters: " +
                votes.percentageNullVotes(votes.getVotersTotal(), votes.getNullVotes()) + "%");

        System.out.println("---------------------------------------------------------------------------");
    }
}