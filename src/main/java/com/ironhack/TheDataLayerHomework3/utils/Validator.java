package com.ironhack.TheDataLayerHomework3.utils;

import com.ironhack.TheDataLayerHomework3.models.Opportunity;
import com.ironhack.TheDataLayerHomework3.models.SalesRep;
import com.ironhack.TheDataLayerHomework3.enums.Validation;
import com.ironhack.TheDataLayerHomework3.repository.SalesRepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class Validator {

    @Autowired
    SalesRepRepository salesRepRepository;

    public boolean applyValidation(Validation validation, String input) {
        boolean isValid = false;

        switch (validation) {
            case COUNTRY -> isValid = isValidCountryName(input);
            case NAME -> isValid = isValidName(input);
            case STRING -> isValid = isValidString(input);
            case EMAIL -> isValid = isValidEmail(input);
            case PHONE -> isValid = isValidPhoneNumber(input);
            case COMMAND -> isValid = isValidCommand(input);
            case OPPORTUNITY -> isValid = isValidOpportunityId(input);
            case SALESREP -> isValid = isValidSalesRepId(input);
        }

        return isValid;
    }

    public Boolean isValidCountryName(String input) {

        Set<String> countries = new HashSet<>(Arrays.stream(COUNTRIES_ARR).map(String::toLowerCase).collect(Collectors.toList()));

        if (countries.contains(input.toLowerCase())) {
            return true;
        } else {
            Utils.printLikeError("Input a valid country");
            return false;
        }

    }

    public Boolean isValidName(String name) {
        Pattern pattern = Pattern.compile("^[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+\\s[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+$");
        Matcher matcher = pattern.matcher(name);

        if (matcher.matches()) {
            return true;
        } else {
            Utils.printLikeError("Name format must be Name Surname");
            return false;
        }
    }

    public Boolean isValidString(String input) {

        String[] noStringChar = {"%", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "/", "!", "(", ")", "+", "*", "<", ">"};

        for (String each : noStringChar) {
            if (input.replaceAll("\\s+", "").contains(each)) {
                Utils.printLikeError("Input a string without spacial characters");
                return false;
            } else return true;
        }

        return false;
    }

    public Boolean isValidOpportunityId(String input) {

//        if (!OpportunityNavigation.opportunityList.isEmpty())
//            for (Opportunity opportunity : OpportunityNavigation.opportunityList) {
//                if (opportunity.getId().equals(input)) return true;
//            }
//        Utils.printLikeError("Input a valid Opportunity ID");
        return false;
    }

    public Boolean isValidSalesRepId(String input) {
        Optional<SalesRep> foundSalesRep = salesRepRepository.findById(input);

        if (foundSalesRep.isPresent()) return true;

        Utils.printLikeError("Input a valid Sales Rep ID");
        return false;
    }

    public Boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        //Pattern pattern = Pattern.compile("^(.+)@(\\S+)$");
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            return true;
        } else {
            Utils.printLikeError("Email address invalid");
            return false;
        }
    }

    public Boolean isValidCommand(String input) {

        input = input.toLowerCase().trim();

        if (input.equals("new lead")) {
            return true;
        } else if (input.equals("show leads")) {
            return true;
        } else if (input.equals("lookup lead")) {
            return true;
        } else if (input.equals("convert lead")) {
            return true;
        } else if (input.equals("change status")) {
            return true;
        } else if (input.equals("exit")) {
            return true;
        } else {
            Utils.clearConsole();
            Utils.printLikeError("Input one of the valid commands");
        }

        return false;
    }

    public Boolean isValidPhoneNumber(String input) {

        Pattern pattern = Pattern.compile(
                "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                        + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                        + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$");

        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            return true;
        } else {
            Utils.printLikeError("Invalid Phone Number");
            return false;
        }
    }

    public final String[] COUNTRIES_ARR = new String[]{"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola",
            "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria",
            "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin",
            "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil",
            "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia",
            "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile",
            "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo",
            "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire",
            "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica",
            "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea",
            "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland",
            "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories",
            "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada",
            "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti",
            "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary",
            "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy",
            "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of",
            "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon",
            "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau",
            "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali",
            "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico",
            "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco",
            "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia",
            "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway",
            "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn",
            "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda",
            "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino",
            "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)",
            "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands",
            "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands",
            "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan",
            "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey",
            "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom",
            "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam",
            "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen",
            "Yugoslavia", "Zambia", "Zimbabwe", "Palestine"
    };

}

