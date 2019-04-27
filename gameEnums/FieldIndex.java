package jakubmajchrzak.gameEnums;

import java.util.ArrayList;
import java.util.List;

public enum FieldIndex {

    A1, A4, A7,

    B2, B4, B6,

    C3, C4, C5,

    D1, D2, D3, D5, D6, D7,

    E3, E4, E5,

    F2, F4, F6,

    G1, G4, G7;

    public List<FieldIndex> getNeighborhood() {
        List<FieldIndex> neighborhood = new ArrayList<>();

        if(this == A7 || this == G7 || this == G1 || this == A1) { //Outer corners
            if(this == A7 || this == G7) { //Outer top corners
                neighborhood.add(D7);
                if(this == A7) {
                    neighborhood.add(A4);
                }
                else {
                    neighborhood.add(G4);
                }
            }
            else {
                neighborhood.add(D1);
                if(this == G1) {
                    neighborhood.add(G4);
                }
                else {
                    neighborhood.add(A4);
                }
            }
        }

        else if(this == D7 || this == G4 || this == D1 || this == A4) { //Outer centers
            if(this == D7 || this == G4) { //Outer second quarter centers
                neighborhood.add(G7);
                if(this == D7) {
                    neighborhood.add(D6);
                    neighborhood.add(A7);
                }
                else {
                    neighborhood.add(F4);
                    neighborhood.add(G1);
                }
            }
            else {
                neighborhood.add(A1);
                if(this == D1) {
                    neighborhood.add(G1);
                    neighborhood.add(D2);
                }
                else  {
                    neighborhood.add(B4);
                    neighborhood.add(A7);
                }
            }
        }

        else if(this == B6 || this == F6 || this == F2 || this == B2) { //Central corners
            if(this == B6 || this == F6) { //Central top corners
                neighborhood.add(D6);
                if(this == B6) {
                    neighborhood.add(B4);
                }
                else {
                    neighborhood.add(F4);
                }
            }
            else {                          //Central bottom corners
                neighborhood.add(D2);
                if(this == F2) {
                    neighborhood.add(F4);
                }
                else {
                    neighborhood.add(B4);
                }
            }
        }

        else if(this == D6 || this == F4 || this == D2 || this == B4) { //Central centers
            if(this == D6 || this == F4) {
                neighborhood.add(F6);
                if(this == D6) {
                    neighborhood.add(D5);
                    neighborhood.add(D7);
                    neighborhood.add(B6);
                }
                else {
                    neighborhood.add(E4);
                    neighborhood.add(G4);
                    neighborhood.add(F2);
                }
            }
            else {
                neighborhood.add(B2);
                if(this == D2) {
                    neighborhood.add(D1);
                    neighborhood.add(D3);
                    neighborhood.add(F2);
                }
                else {
                    neighborhood.add(A4);
                    neighborhood.add(C4);
                    neighborhood.add(B6);
                }
            }
        }

        else if(this == C5 || this == E5 || this == E3 || this == C3) { //Inner corners
            if (this == C5 || this == E5) {
                neighborhood.add(D5);
                if(this == C5) {
                    neighborhood.add(C4);
                }
                else {
                    neighborhood.add(E4);
                }
            }
            else {
                neighborhood.add(D3);
                if(this == E3) {
                    neighborhood.add(E4);
                }
                else {
                    neighborhood.add(C4);
                }
            }
        }

        else { //Inner centers
            if(this == D5 || this == E4) {
                neighborhood.add(E5);
                if(this == D5) {
                    neighborhood.add(C5);
                    neighborhood.add(D6);
                }
                else {
                    neighborhood.add(E3);
                    neighborhood.add(F4);
                }
            }
            else {
                neighborhood.add(C3);
                if(this == D3) {
                    neighborhood.add(D2);
                    neighborhood.add(E3);
                }
                else {
                    neighborhood.add(C5);
                    neighborhood.add(B4);
                }
            }
        }

        return neighborhood;
    }
}
