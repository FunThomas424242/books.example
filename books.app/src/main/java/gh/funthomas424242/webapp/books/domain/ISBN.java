package gh.funthomas424242.webapp.books.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * International Standard Book Number. Es gibt zwei Formate: ISBN-10 (alt) und
 * ISBN-13 (neu). Zur Konvertierung von ISBN-10 in ISBN-13 wird der Prefix 978
 * vorangestellt und die Prüfsumme mit dem ISBN-13 Algorithmus neu berechnet.
 * 
 * @author huluvu424242
 * 
 */
@Entity
public class ISBN {

	@Id
	@GeneratedValue
	protected long id;

	protected String prefix;
	protected String gruppenNummer;
	protected String verlagsNummer;
	protected String bandTitelNummer;
	protected String pruefZiffer;

	protected ISBN() {

	}

	public ISBN(final String isbnraw) {
		this(parseIntoParts(isbnraw));
	}

	protected ISBN(String[] isbnParts) {
		this(isbnParts[0], isbnParts[1], isbnParts[2], isbnParts[3],
				isbnParts[4]);
	}

	public ISBN(String prefix, String gruppenNummer, String verlagsNummer,
			String bandTitelNummer, String pruefZiffer) {
		super();
		this.prefix = prefix;
		this.gruppenNummer = gruppenNummer;
		this.verlagsNummer = verlagsNummer;
		this.bandTitelNummer = bandTitelNummer;
		this.pruefZiffer = pruefZiffer;
	}

	@Override
	public String toString() {
		final StringBuffer buf = new StringBuffer();
		if (prefix.length() > 0) {
			buf.append(prefix);
			buf.append("-");
		}

		buf.append(gruppenNummer);
		buf.append("-");

		buf.append(verlagsNummer);
		buf.append("-");

		buf.append(bandTitelNummer);
		buf.append("-");

		buf.append(pruefZiffer);
		return buf.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bandTitelNummer == null) ? 0 : bandTitelNummer.hashCode());
		result = prime * result
				+ ((gruppenNummer == null) ? 0 : gruppenNummer.hashCode());
		result = prime * result
				+ ((pruefZiffer == null) ? 0 : pruefZiffer.hashCode());
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
		result = prime * result
				+ ((verlagsNummer == null) ? 0 : verlagsNummer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ISBN other = (ISBN) obj;
		if (bandTitelNummer == null) {
			if (other.bandTitelNummer != null)
				return false;
		} else if (!bandTitelNummer.equals(other.bandTitelNummer))
			return false;
		if (gruppenNummer == null) {
			if (other.gruppenNummer != null)
				return false;
		} else if (!gruppenNummer.equals(other.gruppenNummer))
			return false;
		if (pruefZiffer == null) {
			if (other.pruefZiffer != null)
				return false;
		} else if (!pruefZiffer.equals(other.pruefZiffer))
			return false;
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
			return false;
		if (verlagsNummer == null) {
			if (other.verlagsNummer != null)
				return false;
		} else if (!verlagsNummer.equals(other.verlagsNummer))
			return false;
		return true;
	}

	public static ISBN parseFromString(String isbnraw)
			throws InvalidISBNException {
		if (isbnraw == null) {
			throw new InvalidISBNException(isbnraw);
		}
		final String[] isbnParts = parseIntoParts(isbnraw);
		final int partCount = isbnParts.length;

		switch (partCount) {
		case 5: {
			return new ISBN(isbnParts[0], isbnParts[1], isbnParts[2],
					isbnParts[3], isbnParts[4]);
		}
		case 4: {
			return new ISBN10(isbnParts[0], isbnParts[1], isbnParts[2],
					isbnParts[3]);
		}
		default:
			throw new InvalidISBNException(isbnraw);
		}
		// not reachable
	}

	private static String[] parseIntoParts(String isbnraw) {
		final String[] parts = isbnraw.split("-");
		return parts;
	}

	public boolean isValid() {

		final StringBuffer numberPart = new StringBuffer();
		numberPart.append(prefix);
		numberPart.append(gruppenNummer);
		numberPart.append(verlagsNummer);
		numberPart.append(bandTitelNummer);

		if (numberPart.length() != 12) {
			return false;
		} else {
			final String berechnetePruefziffer = berechnePruefziffer(numberPart);
			return this.pruefZiffer.equals(berechnetePruefziffer);
		}
	}

	protected String berechnePruefziffer(final StringBuffer numberPart) {

		int summe = 0;
		for (int i = 0; i < 11; i = i + 2) {
			/* berechne *1 */{
				final char c = numberPart.charAt(i);
				final String digit = Character.toString(c);
				int value = Integer.parseInt(digit);
				summe = summe + value;
			}
			/* berechne *3 */{
				final char c = numberPart.charAt(i + 1);
				final String digit = Character.toString(c);
				int value = Integer.parseInt(digit);
				summe = summe + (value * 3);
			}
		}
		int remainderMod10 = summe % 10;
		int pruefDigit = 10 - remainderMod10;
		return Integer.toString(pruefDigit);
	}

	public String getPrefix() {
		return prefix;
	}

	public String getGruppenNummer() {
		return gruppenNummer;
	}

	public String getVerlagsNummer() {
		return verlagsNummer;
	}

	public String getBandTitelNummer() {
		return bandTitelNummer;
	}

	public String getPruefZiffer() {
		return pruefZiffer;
	}

}
