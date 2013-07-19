
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This class calculates all the mobile Orthodox holy days.
 *
 * @author Drakoulelis <drakouleli at ceid.upatras.gr>
 */
public final class EasterCalculator {

    private Calendar easter;
    private Calendar forefathers;
    private Calendar george;
    private Calendar mark;
    private Calendar cloe;

    /**
     *
     * @param year The year to search dates for. It must be over 1582.
     */
    public EasterCalculator(int year) {
	if (year <= 1582) {
	    throw new IllegalArgumentException("Algorithm invalid before April 1583");
	}
	easter = calculateEaster(year);
	forefathers = calculateSundayOfTheForefathers(year);
	george = calculateSaintGeorge(year);
	mark = calculateMarkTheEvangelist(year);
    }

    private GregorianCalendar calculateEaster(int year) {
	int e = 10;

	if (year > 1600) {
	    int y2 = (int) Math.floor(year / 100);
	    e = 10 + y2 - 16 - (int) Math.floor((y2 - 16) / 4);
	}

	int G = year % 19;
	int I = (19 * G + 15) % 30;
	int J = (year + (int) Math.floor(year / 4) + I) % 7;
	int L = I - J;
	int p = L + e;
	int d = 1 + (p + 27 + (int) Math.floor((p + 6) / 40)) % 31;
	int m = 3 + (int) Math.floor((p + 26) / 30) - 1;
	return new GregorianCalendar(year, m, d);
    }

    private GregorianCalendar calculateSundayOfTheForefathers(int year) {
	GregorianCalendar f = new GregorianCalendar(year, 12, 11);

	if (f.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
	    f.add(Calendar.DAY_OF_WEEK, f.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY);
	}

	return f;
    }

    private GregorianCalendar calculateSaintGeorge(int year) {
	GregorianCalendar g = new GregorianCalendar(year, 4, 23);

	if (g.before(easter)) {
	    g.set(year, easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) + 1);
	}

	return g;
    }

    private GregorianCalendar calculateMarkTheEvangelist(int year) {
	GregorianCalendar m = new GregorianCalendar(year, 4, 25);

	if (m.before(george)) {
	    m.set(year, easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) + 2);
	}

	return m;
    }

    private GregorianCalendar calculateCloe(int year) {
	GregorianCalendar c = new GregorianCalendar(year, 2, 13);

	if (c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
	    c.add(Calendar.DAY_OF_WEEK, c.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY);
	}

	return c;
    }

    /**
     * Returns Easter day. Also known as "Πάσχα"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getEaster() {
	return easter;
    }

    /**
     * Returns Sunday of the Forefathers. Also known as "Των Προπατόρων"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getSundayOfTheForefathers() {
	return forefathers;
    }

    /**
     * Returns Saint George day.
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getSaintGeorgeDay() {
	return george;
    }

    /**
     * Returns Mark the Evangelist day.
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getMarkTheEvangelistDay() {
	return mark;
    }

    /**
     * Returns Saint Cloe day.
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getSaintCloeDay() {
	return cloe;
    }

    /**
     * Calculates the start of the Triodion day. Also known as the Publican (or
     * "Τελώνου και Φαρισαίου").
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getPublicanDay() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) - 70);
    }

    /**
     * Calculates the prodigal son day.
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getProdigal() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) - 63);
    }

    /**
     * Calculates the All Souls (A) day. Also known as "Ψυχοσάββατον Α'"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getAllSoulsDayA() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) - 57);
    }

    /**
     * Calculates the All Souls (B) day. Also known as "Ψυχοσάββατον Β'"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getAllSoulsDayB() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) + 48);
    }

    /**
     * Calculates the Carnival day. Also known as "Αποκριά"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getCarnivalDay() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) - 56);
    }

    /**
     * Calculates Shrove Monday. Also known as "Καθαρά Δευτέρα"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getShroveMonday() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) - 48);
    }

    /**
     * Calculates Shrove Thursday. Also known as "Τσικνοπέμπτη"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getShroveThursday() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) - 59);
    }

    /**
     * Calculates Shrovetide Sunday. Also known as "Κυριακή της Τυροφάγου"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getCheeseSunday() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) - 49);
    }

    /**
     * Calculates the Gregory Palamas day.
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getGregoryPalamasDay() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) - 35);
    }

    /**
     * Calculates the Saint Theodore's day.
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getSaintTheodoreDay() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) - 43);
    }

    /**
     * Calculates Sunday of Orthodoxy.
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getSundayOfOrthodoxy() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) - 42);
    }

    /**
     * Calculates Lazarus Saturday.
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getLazarusDay() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) - 8);
    }

    /**
     * Calculates Palm Sunday. Also known as "Κυριακή των Βαίων"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getPalmSunday() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) - 7);
    }

    /**
     * Calculates Holy Monday. Also known as "Μεγάλη Δευτέρα"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getHolyMonday() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) - 6);
    }

    /**
     * Calculates Holy Tuesday. Also known as "Μεγάλη Τρίτη"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getHolyTuesday() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) - 5);
    }

    /**
     * Calculates Holy Wednesday. Also known as "Μεγάλη Τετάρτη"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getHolyWednesday() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) - 4);
    }

    /**
     * Calculates Holy Thursday. Also known as "Μεγάλη Πέμπτη"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getHolyThursday() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) - 3);
    }

    /**
     * Calculates Holy Friday. Also known as "Μεγάλη Παρασκευή"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getHolyFriday() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) - 2);
    }

    /**
     * Calculates Holy Saturday. Also known as "Μεγάλο Σάββατο"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getHolySaturday() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) - 1);
    }

    /**
     * Calculates Easter Monday. Also known as "Δευτέρα του Πάσχα"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getEasterMonday() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) + 1);
    }

    /**
     * Calculates Easter Tuesday. Also known as "Τρίτη του Πάσχα"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getEasterTuesday() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) + 2);
    }

    /**
     * Calculates Easter Wednesday. Also known as "Τετάρτη του Πάσχα"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getEasterWednesday() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) + 3);
    }

    /**
     * Calculates Easter Thursday. Also known as "Πέμπτη του Πάσχα"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getEasterThursday() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) + 4);
    }

    /**
     * Calculates Easter Friday. Also known as "Παρασκεύη του Πάσχα"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getEasterFriday() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) + 5);
    }

    /**
     * Calculates Easter Saturday. Also known as "Σάββατο του Πάσχα"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getEasterSaturday() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) + 6);
    }

    /**
     * Calculates the Life Giving Spring day. Also known as "Ζωοδόχου Πηγής"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getLifeGivingSpringDay() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) + 5);
    }

    /**
     * Calculates Thomas Sunday. Also known as "Κυριακή του Θωμά"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getThomasSunday() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) + 7);
    }

    /**
     * Calculates the Myrrhbearers day. Also known as "Μυροφόρα"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getMyrrhbearersDay() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) + 14);
    }

    /**
     * Calculates the Paralytic's day. Also known as "Βηθεσδά"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getParalyticDay() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) + 21);
    }

    /**
     * Calculates the Ascension day. Also known as "Ανάληψη"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getAscensionDay() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) + 39);
    }

    /**
     * Calculates the Pentecost day. Also known as "Πεντηκοστή"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getPentecostDay() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) + 49);
    }

    /**
     * Calculates the All Saints day. Also known as "Αγίων Πάντων"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getAllSaintsDay() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) + 56);
    }

    /**
     * Calculates the Holy Spirit's day. Also known as "Αγίου Πνεύματος"
     *
     * @return a Calendar object representing the day.
     */
    public Calendar getHolySpiritDay() {
	return new GregorianCalendar(easter.get(Calendar.YEAR), easter.get(Calendar.MONTH), easter.get(Calendar.DAY_OF_MONTH) + 50);
    }
}
