package raschet;

import java.util.Scanner;

public class Raschet {

    /**
     * Это поля объекта. Они доступны только конкретному объекту.
     * Исходные данные по таблице (в основной части)
     */
    private int z0; //СОПРОТИВЛЕНИЕ ФИЛЬТРА ПРИСОЕДИНЕНИЯ В РАБОЧЕЙ ФАЗЕ Z0(ОМ)
    private int z2; //СОПРОТИВЛЕНИЕ НАГРУЗКИ В НЕРАБОЧЕЙ ФАЗЕ Z2(ОМ)
    private int z3; //СОПРОТИВЛЕНИЕ ЗАГРАДИТЕЛЯ В РАБОЧЕЙ ФАЗЕ Z3(ОМ)
    private double l; //ДЛИНА ОТВЕТВЛЕНИЯ L(KM)
    private double f; //ВЫСШАЯ ЧАСТОТА КАНАЛА F(КГЦ)
    private double a1; //КОЭФФИЦИЕНТ ЗАТУХАНИЯ ОТВЕТВЛЕНИЯ A1(ДБ/КМ)
    /**
     * Это результат по таблице
     */

    private double k; //КОЭФ. ОТРАЖЕНИЯ ВОЛН К
    /**
     * Исходные данные по таблице (в шапке)
     */
    private int o; //ТОЧКА НЕОДНОРОДНОСТИ ЯВЛЯЕТСЯ ОТВЕТВЛЕНИЕМ?-ДА(O=1),НЕТ(O=0)
    private int i; //ОТВЕТВЛЕНИЕ ИСПОЛЬЗУЕТСЯ ДЛЯ СВЯЗИ?-ДА(I=1),НЕТ(I=0)
    private int n; //ОТВЕТВЛЕНИЕ НЕ ОБРАБОТАНО?-ДА(N=1),НЕТ(N=0)
    private int v; //СХЕМА ПРИСОЕДИНЕНИЯ ФАЗА-ФАЗА?-ДА(V=1),НЕТ(V=0)
    private int r3; //ЗАГРАДИТЕЛИ В СКОЛЬКИХ ФАЗАХ?-R3 = 1,2 ИЛИ 3
    private int p; //ПРИСОЕДИНЕНИЕ В КОНЦЕ ОТВЕТВЛ.К ОДНОЙ ФАЗЕ?-ДА(P=1),НЕТ(P=0)

    private float num; // номер схемы (вычисляется в результате предыдущих полей)
    /**
     * промежуточные поля
     */
    private double a;
    private double e;
    private double k5;
    private double k6;
    private double l1;
    private double q0;
    private double q1;
    private double q2;
    private double q3;
    private double z1;

    public void pechat() {
        System.out.println("Исходные данные:");
        System.out.println("СОПРОТИВЛЕНИЕ ФИЛЬТРА ПРИСОЕДИНЕНИЯ В РАБОЧЕЙ ФАЗЕ  Z0 = " + z0 + "   ОМ");
        System.out.println("СОПРОТИВЛЕНИЕ НАГРУЗКИ В НЕРАБОЧЕЙ ФАЗЕ             Z2 = " + z2 + "   ОМ");
        System.out.println("СОПРОТИВЛЕНИЕ ЗАГРАДИТЕЛЯ В РАБОЧЕЙ ФАЗЕ            Z3 = " + z3 + "   ОМ");
        System.out.println("ДЛИНА ОТВЕТВЛЕНИЯ                                   L  = " + l + " KM");
        System.out.println("ВЫСШАЯ ЧАСТОТА КАНАЛА                               F  = " + f + " КГЦ");
        System.out.println("КОЭФФИЦИЕНТ ЗАТУХАНИЯ ОТВЕТВЛЕНИЯ                   A1 = " + a1 + " ДБ/КМ");
        System.out.println("ТОЧКА НЕОДНОРОДНОСТИ " + (o==1 ? "" : "НЕ ") + "ЯВЛЯЕТСЯ ОТВЕТВЛЕНИЕМ");
        System.out.println("ОТВЕТВЛЕНИЕ " + (i==1 ? "" : "НЕ ") + "ИСПОЛЬЗУЕТСЯ ДЛЯ СВЯЗИ" );
        System.out.println("ОТВЕТВЛЕНИЕ " + (n==0 ? "" : "НЕ ") + "ОБРАБОТАНО ЗАГРАДИТЕЛЕМ");
        System.out.println("СХЕМА ПРИСОЕДИНЕНИЯ ФАЗА-" + (v==0 ? "ЗЕМЛЯ" : "ФАЗА "));
        System.out.println(r3>0 && r3<4 ? "ЗАГРАДИТЕЛИ В " + r3 + "ФАЗАХ" : "НЕДОПУСТИМОЕ ЗНАЧЕНИЕ R3");
        System.out.println("ПРИСОЕДИНЕНИЕ " + (p==1 ? "" : "НЕ ") + "В КОНЦЕ ОТВЕТВЛ.К ОДНОЙ ФАЗЕ");
        System.out.println();
        System.out.println("Номер схемы " + num);
        System.out.println("a = " + a);
        System.out.println("e = " + e);
        System.out.println("k5 = " + k5);
        System.out.println("k6 = " + k6);
        System.out.println("l1 = " + l1);
        System.out.println("q0 = " + q0);
        System.out.println("q1 = " + q1);
        System.out.println("q2 = " + q2);
        System.out.println("q3 = " + q3);
        System.out.println("z1 = " + z1);
    }

    public void vvod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ТОЧКА НЕОДНОРОДНОСТИ ЯВЛЯЕТСЯ ОТВЕТВЛЕНИЕМ?-ДА(O=1),НЕТ(O=0) ");
        System.out.print("ВВЕДИТЕ ЗНАЧЕНИЕ O: ");
        this.o = scanner.nextInt();
        if (this.o == 1) {
            System.out.println("ОТВЕТВЛЕНИЕ ИСПОЛЬЗУЕТСЯ ДЛЯ СВЯЗИ?-ДА(I=1),НЕТ(I=0)");
            System.out.print("ВВЕДИТЕ ЗНАЧЕНИЕ I: ");
            this.i = scanner.nextInt();
        } else {
            // Ввод из подпрограммы ВАРИАНТ СХЕМЫ 1.К-Т ОТРАЖЕНИЯ ВОЛН ОТ КОНЦА ВЛ ..."
            System.out.println("ВВЕДИТЕ ЗНАЧЕНИЕ СОПРОТИВЛЕНИЯ ФИЛЬТРА ПРИСОЕДИНЕНИЯ Z0(в ОМ) ");
            this.z0 = scanner.nextInt();
            System.out.println("ВВЕДИТЕ ЗНАЧЕНИЕ СОПРОТИВЛЕНИЯ ЗАГРАДИТЕЛЯ Z3(в ОМ) ");
            this.z3 = scanner.nextInt();
            System.out.println("СХЕМА ПРИСОЕДИНЕНИЯ ФАЗА-ФАЗА?-ДА(V=1),НЕТ(V=0) ");
            System.out.print("ВВЕДИТЕ ЗНАЧЕНИЕ V: ");
            this.v = scanner.nextInt();
            this.num = (float) (this.v == 0 ? 1.1 : 1.2);
        }
    }

    public static void main(String[] args) {
        Raschet schema = new Raschet();
        schema.vvod();
        schema.pechat();
    }
}

        /* double k;
        Scanner scanner = new Scanner(System.in);
        System.out.println("ТОЧКА НЕОДНОРОДНОСТИ ЯВЛЯЕТСЯ ОТВЕТВЛЕНИЕМ?-ДА(O=1),НЕТ(O=0) ");
        System.out.print("ВВЕДИТЕ ЗНАЧЕНИЕ O: ");
        int o = scanner.nextInt();
        if (o == 1) {
            System.out.println("ОТВЕТВЛЕНИЕ ИСПОЛЬЗУЕТСЯ ДЛЯ СВЯЗИ?-ДА(I=1),НЕТ(I=0)");
            System.out.print("ВВЕДИТЕ ЗНАЧЕНИЕ I: ");
            int i = scanner.nextInt();
            double e = 0;
            if (i == 0) {
                //РАСЧЕТ К-ТА ОТРАЖЕНИЯ ВОЛН ОТ ОТВЕТВЕТВЛЕНИЯ, НЕ ИСПОЛЬЗУЕМОГО ДЛЯ СВЯЗИ (СХЕМЫ С 5 ПО 9)
                System.out.println("ОТВЕТВЛЕНИЕ НЕ ОБРАБОТАНО?-ДА(N=1),НЕТ(N=0)");
                System.out.print("ВВЕДИТЕ ЗНАЧЕНИЕ N: ");
                int n = scanner.nextInt();
                if (n == 0) {
                    //РАСЧЕТ К-ТА ОТР.ВОЛН ОТ ОТВЕТВЛЕНИЯ,НЕИСПОЛЬЗУЕМОГО ДЛЯ СВЯЗИ
                    //И ОБРАБОТАННОГО ЗАГРАДИТЕЛЕМ (СХЕМЫ 5-8)
                    System.out.println("отладочная информация (СХЕМЫ 5-8)");
                    System.out.print("ВВЕДИТЕ ЗНАЧЕНИЕ СОПРОТИВЛЕНИЯ ЗАГРАДИТЕЛЯ Z3(ОМ): ");
                    int z3 = scanner.nextInt();
                    double q3 = (double) z3 / 380;
                    double q0 = 1.71;
                    System.out.println("СХЕМА ПРИСОЕДИНЕНИЯ ФАЗА-ФАЗА?-ДА(V=1),НЕТ(V=0)");
                    System.out.print("ВВЕДИТЕ ЗНАЧЕНИЕ V: ");
                    int v = scanner.nextInt();
                    if (v == 0) {
                        // СХЕМЫ 5-7
                        System.out.println("ЗАГРАДИТЕЛИ В СКОЛЬКИХ ФАЗАХ? - R3 = 1, 2 ИЛИ 3");
                        System.out.print("ВВЕДИТЕ ЗНАЧЕНИЕ R3: ");
                        int r3 = scanner.nextInt();
                        switch (r3) {
                            case 1 -> { // СХЕМА 5 (ЗАГРАДИТЕЛИ В 1 ФАЗЕ)
                                e = 4 * q3 * q0 / (2 * q3 + 3 * q0);
                                System.out.println("ВАРИАНТ СХЕМЫ 5. ИСХОДНЫЕ ДАННЫЕ: Z3 = " + z3);
                            }
                            case 2 -> { // СХЕМА 6 (ЗАГРАДИТЕЛИ В 2 ФАЗАХ)
                                e = (6 * q3 + 4 * q0 * q3 + 5 * q0) / (4 + 2 * q3 + 3 * q0 + 3 * q0 / q3);
                                System.out.println("ВАРИАНТ СХЕМЫ 6. ИСХОДНЫЕ ДАННЫЕ: Z3 = " + z3);
                            }
                            case 3 -> { // СХЕМА 7 (ЗАГРАДИТЕЛИ В 3 ФАЗАХ)
                                e = 2 * q3;
                                System.out.println("ВАРИАНТ СХЕМЫ 7. ИСХОДНЫЕ ДАННЫЕ: Z3 = " + z3);
                            }
                            default -> System.out.println("Вы ввели неверное значение R3 (не 1 или 2, или 3");
                        }
                    } else {
                        // СХЕМА 8
                        e = 2 * q3;
                        System.out.println("ВАРИАНТ СХЕМЫ 8. ИСХОДНЫЕ ДАННЫЕ: Z3 = " + z3);
                    }
                    k = -1 / (1 + e);
                } else {
                    // СХЕМА 9
                    System.out.println("отладочная информация (СХЕМА 9)");
                    System.out.print("ВВЕДИТЕ ДЛИНУ ОТВЕТВЛЕНИЯ L(KM): ");
                    int l = scanner.nextInt();
                    System.out.print("ВВЕДИТЕ К-Т ЗАТУХАНИЯ ОТВЕТВЛЕНИЯ A1(ДБ/КМ): ");
                    double a1 = scanner.nextDouble();
                    double a = 0.115 * a1 * l;
                    k = -(1 - a) / (1 + a);
                    System.out.println("ВАРИАНТ СХЕМЫ 9. ИСХОДНЫЕ ДАННЫЕ: L= " + l + " A1 = " + a1);
                }
            } else {
                // ОТВЕТВЛЕНИЕ ИСПОЛЬЗУЕТСЯ ДЛЯ СВЯЗИ (СХЕМЫ ПО 2.1 - 4)
                System.out.print("ВВЕДИТЕ ДЛИНУ ОТВЕТВЛЕНИЯ L(KM): (дробная часть через запятую) ");
                double l = scanner.nextDouble();
                System.out.print("ВВЕДИТЕ ВЫСШУЮ ЧАСТОТУ КАНАЛА F(КГЦ): ");
                double f = scanner.nextDouble();
                double l1 = 19 / f;
                System.out.println("отладочная информация l = " + l + " l1 = " + l1 + " l <= l1: " + (l <= l1));
                if (l <= l1) {
                    // РАСЧЕТ КОЭФФИЦИЕНТА ОТРАЖЕНИЯ ВОЛН ОТ КОРОТКОГО ОТВЕТВЕТВЛЕНИЯ,
                    // ИСПОЛЬЗУЕМОГО ДЛЯ СВЯЗИ (СХЕМЫ 3 И 4)
                    System.out.println("отладочная информация (СХЕМЫ 3 И 4)");
                    System.out.print("ВВЕДИТЕ СОПРОТИВЛЕНИЕ ФИЛЬТРА ПРИС.В РАБ.ФАЗЕ Z0(ОМ): ");
                    int z0 = scanner.nextInt();
                    System.out.print("ВВЕДИТЕ СОПРОТИВЛЕНИЕ ЗАГРАДИТЕЛЯ В РАБ.ФАЗЕ Z3(ОМ): ");
                    int z3 = scanner.nextInt();
                    double z1 = (z0 * z3) / (z0 + z3);
                    double q1 = z1 / 380;
                    System.out.println("ПРИСОЕДИНЕНИЕ В КОНЦЕ ОТВЕТВЛ.К ОДНОЙ ФАЗЕ?-ДА(P=1),НЕТ(P=0)");
                    System.out.print("ВВЕДИТЕ ЗНАЧЕНИЕ P: ");
                    int p = scanner.nextInt();
                    if (p == 0) {
                        //РАСЧЕТ К-ТА ОТР. ВОЛН ОТ КОРОТКОГО ОТВ., ИСПОЛЬЗ. ДЛЯ СВЯЗИ С
                        //ПРИСОЕД.К ДВУМ ИЛИ БОЛЕЕ ФАЗАМ (СХЕМА 4)
                        e = q1;
                        System.out.println("ВАРИАНТ СХЕМЫ 4. ИСХОДНЫЕ ДАННЫЕ: L= " + l + " F = " + f + " Z0 = " + z0 + " Z3 = " + z3);
                    } else {
                        // СХЕМА 3
                        System.out.print("ВВЕДИТЕ СОПРОТИВЛЕНИЕ НАГРУЗКИ В НЕРАБ.ФАЗЕ Z2(ОМ): ");
                        int z2 = scanner.nextInt();
                        double q2 = (double) z2 / 380;
                        double q0 = 1.71;
                        e = 2 * (q2 * q0 + 2 * q1 * q0 + 6 * q1 * q2) / (2 * q1 + 4 * q2 + 3 * q0);
                        System.out.println("ВАРИАНТ СХЕМЫ 3. ИСХОДНЫЕ ДАННЫЕ: L= " + l + " F = " + f + " Z0 = " + z0 + " Z3 = " + z3 + " Z2 = " + z2);
                    }
                    k = -1 / (1 + e);
                } else {
                    //РАСЧЕТ К-ТА ОТРАЖ. ВОЛН ОТ ДЛИННОГО ОТВЕТВЛЕНИЯ, ИСПОЛЬЗ. ДЛЯ СВЯЗИ
                    //СХЕМЫ 2.1 И 2.2
                    System.out.println("отладочная информация (СХЕМЫ 2.1 И 2.2)");
                    k = Raschet.otKoncaVL();
                    System.out.print("ВВЕДИТЕ К-Т ЗАТУХАНИЯ ОТВЕТВЛЕНИЯ A1(ДБ/КМ) (ввод дробной части через запятую): ");
                    double a1 = scanner.nextDouble();
                    double a = a1 * l + 4.34294 * Math.log(Math.abs(1 / k));
                    k = -(1 + Math.exp(-0.23 * a))/(3 - Math.exp(-0.23 * a));
                    System.out.println("ВАРИАНТ СХЕМЫ 2. ИСХОДНЫЕ ДАННЫЕ: L= " + l + " F = " + f + " A1 = " + a1);
                }
            }
            System.out.println("КОЭФ. ОТРАЖЕНИЯ ВОЛН ОТ ОТВЕТВЛЕНИЯ K= " + k);
        }
        else {
            k = Raschet.otKoncaVL();
            System.out.println("ВАРИАНТ СХЕМЫ 1.К-Т ОТРАЖЕНИЯ ВОЛН ОТ КОНЦА ВЛ К=" + k);
        }
    }

    private static double otKoncaVL() {
        // подпрограмма 1. (Схемы 1.1, 1.2, 2.1, 2.2)
        Scanner scanner = new Scanner(System.in);
        System.out.println("ВВЕДИТЕ ЗНАЧЕНИЕ СОПРОТИВЛЕНИЯ ФИЛЬТРА ПРИСОЕДИНЕНИЯ Z0(в ОМ) ");
        int z0 = scanner.nextInt();
        System.out.println("ВВЕДИТЕ ЗНАЧЕНИЕ СОПРОТИВЛЕНИЯ ЗАГРАДИТЕЛЯ Z3(в ОМ) ");
        int z3 = scanner.nextInt();
        double z1 = (z0 * z3) / (z0 + z3);
        System.out.println("СХЕМА ПРИСОЕДИНЕНИЯ ФАЗА-ФАЗА?-ДА(V=1),НЕТ(V=0) ");
        System.out.print("ВВЕДИТЕ ЗНАЧЕНИЕ V: ");
        int v = scanner.nextInt();
        double k;
        double q1;
        if (v == 0) {
            // схема 1.1 или 2.1
            q1 = z1 / 380;
            // ВЫЧИСЛЕНИЕ КВЛ1=K5
            double k5 = 0.55 * (q1 - 2.12) / (q1 + 1.16);
            // ВЫЧИСЛЕНИЕ КВЛ2=K6
            double k6 = (q1 - 0.1) / (q1 + 1.24);
            k = Math.abs(k5) <= Math.abs(k6) ? k6 : k5;
        } else {
            // схема 1.2 или 2.2
            q1 = z1 / 760;
            k = (q1 - 1) / (q1 + 1);
        }
        // вывод данных
        System.out.println("ИСХОДНЫЕ ДАННЫЕ:Z0 = " + z0 + " Z3 = " + z3);
        return k;
    }
}*/