import java.util.ArrayList;
import java.util.Map;

//plays.json...
// {
//   "hamlet": {"name": "Hamlet", "type": "tragedy"},
//   "as-like": {"name": "As You Like It", "type": "comedy"},
//   "othello": {"name": "Othello", "type": "tragedy"},
// }

// //invoices.json...
// [
//   {
//       "customer": "BigCo",
//       "performances": [
//           {"playID": "Hamlet", "audience": 55},
//           {"playID": "as-like", "audience": 35},
//           {"playID": "othello", "audience": 40},
//       ]
//   }
// ]

// // The following function is used to print bill details
// function statement (invoice, plays) {
//   let totalAmount = 0;
//   let volumeCredits = 0;
//   let result = `Statement for ${invoice.customer}\n`;
//   const format = new Intl.NumberFormat("en-US", { style: "currency", currency: "USD", minimumFractionDigits: 2 }).format;
//   for (let perf of invoice.performances) {
//       const play = plays[perf.playID];
//       let thisAmount = 0;

//       switch (play.type) {
//           case "tragedy":
//               thisAmount = 40000;
//               if (perf.audience > 30) {
//                   thisAmount += 1000 * (perf.audience - 30);
//               }
//               break;
//           case "comedy":
//               thisAmount = 30000;
//               if (perf.audience > 20) {
//                   thisAmount += 10000 + 500 * (perf.audience - 20);
//               }
//               thisAmount += 300 * perf.audience;
//               break;
//           default:
//               throw new Error(`unknow type: ${play.type}`);
//       }
//       // add volume credits
//       volumeCredits += Math.max(perf.audience - 30, 0);
//       // add extra credit for every ten comedy attendees
//       if ("comedy" === play.type) {
//           volumeCredits += Math.floor(perf.audience / 5);
//       }
//       // print line for this order
//       result += `${play.name}: ${format(thisAmount/100)} (${perf.audience} seats)\n`;
//       totalAmount += thisAmount;
//   }
//   result += `Amount owed is ${format(totalAmount/100)}\n`;
//   result += `You earned ${volumeCredits} credits\n`;
//   return result;
// }


public class Main {
    public static ArrayList<Play> plays;
    public static Invoice invoice;
    public static void main(String[] args) throws Exception {
        plays = new ArrayList<Play>();
        plays.add(new Play("hamlet", "hamlet", Play.TRAGEDY));
        plays.add(new Play("as-like", "as-like", Play.COMEDY));
        plays.add(new Play("othello", "othello", Play.TRAGEDY));

        invoice = new Invoice("BigCo");
        invoice.addPerfomance(new Performance("hamlet", 55));
        invoice.addPerfomance(new Performance("as-like", 35));
        invoice.addPerfomance(new Performance("othello", 40));

        System.out.print(statement(invoice, plays));
    }

    public static String statement(Invoice invoice, ArrayList<Play> plays) throws Exception {
        int totalAmount = 0;
        int volumeCredits = 0;
        String result = "Statement for " + invoice.getCustomer() + "\n";

        for (Performance performance: invoice.getPerformances()) {
            final Play play = getPlay(performance.getPlayId());

            int thisAmount = 0;
            switch (play.getType()) {
                case Play.TRAGEDY:
                    thisAmount = 40000;
                    if (performance.getAudience() > 30) {
                        thisAmount += 1000 * (performance.getAudience() - 30);
                    }
                    break;
                case Play.COMEDY:
                    thisAmount = 30000;
                    if (performance.getAudience() > 20) {
                        thisAmount += 10000 + 500 * (performance.getAudience() - 20);
                    }
                    break;
                default:
                    throw new Exception("Unknown type " + play.getType());
            }

            volumeCredits += Math.max(performance.getAudience() - 30, 0);
            
            if (play.getType() == Play.COMEDY) {
                volumeCredits += Math.floor(performance.getAudience() / 5);
            }

            result += play.getName() + ": " + thisAmount/100 + "(" + performance.getAudience() + " seats)\n";
            totalAmount += thisAmount;
        }

        result += "Amount owed is " + totalAmount/100;
        result += "Your earned " + volumeCredits + " credits.\n";
        return result;
    }

    public static Play getPlay(String playId) {
        for (Play play: plays) {
            if (play.getId().equals(playId)) {
                return play;
            }
        }
        return null;
    }
}