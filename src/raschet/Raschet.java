package raschet;

import java.util.Scanner;

public class Raschet {

    public int z0; //СОПРОТИВЛЕНИЕ ФИЛЬТРА ПРИСОЕДИНЕНИЯ В РАБОЧЕЙ ФАЗЕ Z0(ОМ)
    public int z2; //СОПРОТИВЛЕНИЕ НАГРУЗКИ В НЕРАБОЧЕЙ ФАЗЕ Z2(ОМ)
    public int z3; //СОПРОТИВЛЕНИЕ ЗАГРАДИТЕЛЯ В РАБОЧЕЙ ФАЗЕ Z3(ОМ)
    public double l; //ДЛИНА ОТВЕТВЛЕНИЯ L(KM)
    public double f; //ВЫСШАЯ ЧАСТОТА КАНАЛА F(КГЦ)
    public double a1; //КОЭФФИЦИЕНТ ЗАТУХАНИЯ ОТВЕТВЛЕНИЯ A1(ДБ/КМ)
    /**
     * Это результат по таблице
     */

    public double k; //КОЭФ. ОТРАЖЕНИЯ ВОЛН К
    /**
     * Исходные данные по таблице (в шапке)
     */
    public int o; //ТОЧКА НЕОДНОРОДНОСТИ ЯВЛЯЕТСЯ ОТВЕТВЛЕНИЕМ?-ДА(O=1),НЕТ(O=0)
    public int i; //ОТВЕТВЛЕНИЕ ИСПОЛЬЗУЕТСЯ ДЛЯ СВЯЗИ?-ДА(I=1),НЕТ(I=0)
    public int n; //ОТВЕТВЛЕНИЕ НЕ ОБРАБОТАНО?-ДА(N=1),НЕТ(N=0)
    public int v; //СХЕМА ПРИСОЕДИНЕНИЯ ФАЗА-ФАЗА?-ДА(V=1),НЕТ(V=0)
    public int r3; //ЗАГРАДИТЕЛИ В СКОЛЬКИХ ФАЗАХ?-R3 = 1,2 ИЛИ 3
    public int p; //ПРИСОЕДИНЕНИЕ В КОНЦЕ ОТВЕТВЛ.К ОДНОЙ ФАЗЕ?-ДА(P=1),НЕТ(P=0)

    public int num; // номер схемы (вычисляется в результате предыдущих полей)
    /**
     * промежуточные поля
     */
    public double a;
    public double e;
    public double k5;
    public double k6;
    public double l1;
    public double q1;
    public double q2;
    public double q3;
    public double z1;

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
        System.out.println("ПРИСОЕДИНЕНИЕ В КОНЦЕ ОТВЕТВЛЕНИЯ " + (p==1 ? "" : "НЕ ") + "К ОДНОЙ ФАЗЕ");
        System.out.println();
        System.out.println("Номер схемы " + num);
        System.out.println("a = " + a);
        System.out.println("e = " + e);
        System.out.println("k5 = " + k5);
        System.out.println("k6 = " + k6);
        System.out.println("l1 = " + l1);
        System.out.println("q1 = " + q1);
        System.out.println("q2 = " + q2);
        System.out.println("q3 = " + q3);
        System.out.println("z1 = " + z1);
        System.out.println();
        System.out.println("КОЭФ. ОТРАЖЕНИЯ ВОЛН ОТ ОТВЕТВЛЕНИЯ K= " + k);
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
            if (this.i == 0) {
                /*ВВОД ДАННЫХ ДЛЯ РАСЧЕТА К-ТА ОТРАЖЕНИЯ ВОЛН ОТ ОТВЕТВЕТВЛЕНИЯ,
                НЕ ИСПОЛЬЗУЕМОГО ДЛЯ СВЯЗИ (СХЕМЫ С 5 ПО 9) */
                System.out.println("ОТВЕТВЛЕНИЕ НЕ ОБРАБОТАНО?-ДА(N=1),НЕТ(N=0)");
                System.out.print("ВВЕДИТЕ ЗНАЧЕНИЕ N: ");
                this.n = scanner.nextInt();
                if (this.n == 0) {
                    /* ВВОД ДАННЫХ ДЛЯ РАСЧЕТА К-ТА ОТР.ВОЛН ОТ ОТВЕТВЛЕНИЯ,
                    НЕИСПОЛЬЗУЕМОГО ДЛЯ СВЯЗИ И ОБРАБОТАННОГО ЗАГРАДИТЕЛЕМ (СХЕМЫ 5-8)*/
                    System.out.print("ВВЕДИТЕ ЗНАЧЕНИЕ СОПРОТИВЛЕНИЯ ЗАГРАДИТЕЛЯ Z3(ОМ): ");
                    this.z3 = scanner.nextInt();
                    System.out.println("СХЕМА ПРИСОЕДИНЕНИЯ ФАЗА-ФАЗА?-ДА(V=1),НЕТ(V=0)");
                    System.out.print("ВВЕДИТЕ ЗНАЧЕНИЕ V: ");
                    this.v = scanner.nextInt();
                    if (v == 0) {
                        // ВВОД ДАННЫХ ДЛЯ СХЕМ 5-7
                        System.out.println("ЗАГРАДИТЕЛИ В СКОЛЬКИХ ФАЗАХ? - R3 = 1, 2 ИЛИ 3");
                        System.out.print("ВВЕДИТЕ ЗНАЧЕНИЕ R3: ");
                        this.r3 = scanner.nextInt();
                        this.num = r3 + 4;
                    } else {
                        this.num = 8;
                    }
                } else {
                    // СХЕМА 9
                    this.num = 9;
                    System.out.print("ВВЕДИТЕ ДЛИНУ ОТВЕТВЛЕНИЯ L(KM): ");
                    this.l = scanner.nextInt();
                    System.out.print("ВВЕДИТЕ К-Т ЗАТУХАНИЯ ОТВЕТВЛЕНИЯ A1(ДБ/КМ): ");
                    this.a1 = scanner.nextDouble();
                }
            } else {
                // ОТВЕТВЛЕНИЕ ИСПОЛЬЗУЕТСЯ ДЛЯ СВЯЗИ (СХЕМЫ ПО 2.1 - 4)
                System.out.print("ВВЕДИТЕ ДЛИНУ ОТВЕТВЛЕНИЯ L(KM): (дробная часть через запятую) ");
                this.l = scanner.nextDouble();
                System.out.print("ВВЕДИТЕ ВЫСШУЮ ЧАСТОТУ КАНАЛА F(КГЦ): ");
                this.f = scanner.nextDouble();
                this.l1 = 19 / this.f;
                if (this.l <= this.l1) {
                    // РАСЧЕТ КОЭФФИЦИЕНТА ОТРАЖЕНИЯ ВОЛН ОТ КОРОТКОГО ОТВЕТВЕТВЛЕНИЯ,
                    // ИСПОЛЬЗУЕМОГО ДЛЯ СВЯЗИ (СХЕМЫ 3 И 4)
                    System.out.print("ВВЕДИТЕ СОПРОТИВЛЕНИЕ ФИЛЬТРА ПРИС.В РАБ.ФАЗЕ Z0(ОМ): ");
                    this.z0 = scanner.nextInt();
                    System.out.print("ВВЕДИТЕ СОПРОТИВЛЕНИЕ ЗАГРАДИТЕЛЯ В РАБ.ФАЗЕ Z3(ОМ): ");
                    this.z3 = scanner.nextInt();
                    System.out.println("ПРИСОЕДИНЕНИЕ В КОНЦЕ ОТВЕТВЛ.К ОДНОЙ ФАЗЕ?-ДА(P=1),НЕТ(P=0) ");
                    System.out.print("ВВЕДИТЕ ЗНАЧЕНИЕ P: ");
                    this.p = scanner.nextInt();
                    if (this.p == 0) {
                        //РАСЧЕТ К-ТА ОТР. ВОЛН ОТ КОРОТКОГО ОТВ., ИСПОЛЬЗ. ДЛЯ СВЯЗИ С
                        //ПРИСОЕД.К ДВУМ ИЛИ БОЛЕЕ ФАЗАМ (СХЕМА 4)
                        this.num = 4;
                    } else {
                        // СХЕМА 3
                        this.num = 3;
                        System.out.print("ВВЕДИТЕ СОПРОТИВЛЕНИЕ НАГРУЗКИ В НЕРАБ.ФАЗЕ Z2(ОМ): ");
                        this.z2 = scanner.nextInt();
                    }
                } else {
                    /*ИСПОЛЬЗ. ДЛЯ СВЯЗИ СХЕМЫ 2.1 И 2.2
                    Ввод из подпрограммы ВАРИАНТ СХЕМЫ 1.К-Т ОТРАЖЕНИЯ ВОЛН ОТ КОНЦА ВЛ ..."*/
                    vvodTchk12();
                    // до этого места и аналогичное место попробовать вынести в еще один метод
                    this.num = this.v == 0 ? 21 : 22;
                    System.out.print("ВВЕДИТЕ К-Т ЗАТУХАНИЯ ОТВЕТВЛЕНИЯ A1(ДБ/КМ) (ввод дробной части через запятую): ");
                    this.a1 = scanner.nextDouble();
                }
            }
        } else {
            // Ввод из подпрограммы ВАРИАНТ СХЕМЫ 1.К-Т ОТРАЖЕНИЯ ВОЛН ОТ КОНЦА ВЛ ..."
            vvodTchk12();
            // до этого места и аналогичное место попробовать вынести в еще один метод
            this.num = this.v == 0 ? 11 : 12;
        }
    }

    public void vvodTchk12() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ВВЕДИТЕ ЗНАЧЕНИЕ СОПРОТИВЛЕНИЯ ФИЛЬТРА ПРИСОЕДИНЕНИЯ Z0(в ОМ) ");
        this.z0 = scanner.nextInt();
        System.out.print("ВВЕДИТЕ ЗНАЧЕНИЕ СОПРОТИВЛЕНИЯ ЗАГРАДИТЕЛЯ Z3(в ОМ) ");
        this.z3 = scanner.nextInt();
        System.out.println("СХЕМА ПРИСОЕДИНЕНИЯ ФАЗА-ФАЗА?-ДА(V=1),НЕТ(V=0) ");
        System.out.print("ВВЕДИТЕ ЗНАЧЕНИЕ V: ");
        this.v = scanner.nextInt();
    }

    public void calc() {
        switch (this.num) {
            case 11 -> {
                this.z1 = (this.z0 * this.z3) / (this.z0 + this.z3);
                this.q1 = this.z1 / 380;
                // ВЫЧИСЛЕНИЕ КВЛ1=K5
                this.k5 = 0.55 * (this.q1 - 2.12) / (this.q1 + 1.16);
                // ВЫЧИСЛЕНИЕ КВЛ2=K6
                this.k6 = (this.q1 - 0.1) / (this.q1 + 1.24);
                this.k = Math.abs(this.k5) <= Math.abs(this.k6) ? this.k6 : this.k5;
            }
            case 12 -> {
                this.z1 = (this.z0 * this.z3) / (this.z0 + this.z3);
                this.q1 = this.z1 / 760;
                this.k = (this.q1 - 1) / (this.q1 + 1);
            }
            case 21 -> {
                this.z1 = (this.z0 * this.z3) / (this.z0 + this.z3);
                this.q1 = this.z1 / 380;
                // ВЫЧИСЛЕНИЕ КВЛ1=K5
                this.k5 = 0.55 * (this.q1 - 2.12) / (this.q1 + 1.16);
                // ВЫЧИСЛЕНИЕ КВЛ2=K6
                this.k6 = (this.q1 - 0.1) / (this.q1 + 1.24);
                this.k = Math.abs(this.k5) <= Math.abs(this.k6) ? this.k6 : this.k5;
                /* до этого места возможно вынесение в отдельный метод вместе с case 11
                а после этого тоже в отдельный вместе с case 22 */
                this.a = this.a1 * this.l + 4.34294 * Math.log(Math.abs(1 / this.k));
                this.k = -(1 + Math.exp(-0.23 * this.a))/(3 - Math.exp(-0.23 * this.a));
            }
            case 22 -> {
                this.z1 = (this.z0 * this.z3) / (this.z0 + this.z3);
                this.q1 = this.z1 / 760;
                this.k = (this.q1 - 1) / (this.q1 + 1);
                /* до этого места возможно вынесение в отдельный метод вместе с case 12
                а после этого тоже в отдельный вместе с case 21 */
                this.a = this.a1 * this.l + 4.34294 * Math.log(Math.abs(1 / this.k));
                this.k = -(1 + Math.exp(-0.23 * this.a))/(3 - Math.exp(-0.23 * this.a));
            }
            case 3 -> {// возможно обьединение с case 40 (середина разная)
                this.z1 = (this.z0 * this.z3) / (this.z0 + this.z3);
                this.q1 = this.z1 / 380;

                this.q2 = (double) this.z2 / 380;
                this.e = 2 * (this.q2 * 1.71 + 2 * this.q1 * 1.71 + 6 * this.q1 * this.q2) / (2 * this.q1 + 4 * this.q2 + 3 * 1.71);

                this.k = -1 / (1 + this.e);
            }
            case 4 -> {// возможно обьединение с case 30 (середина разная)
                this.z1 = (this.z0 * this.z3) / (this.z0 + this.z3);
                this.q1 = this.z1 / 380;

                this.e = this.q1;

                this.k = -1 / (1 + this.e);
            }
            case 5 -> {
                this.q3 = (double) this.z3 / 380;

                this.e = 4 * this.q3 * 1.71 / (2 * this.q3 + 3 * 1.71);

                this.k = -1 / (1 + this.e);
            }
            case 6 -> {
                this.q3 = (double) this.z3 / 380;

                this.e = (6 * this.q3 + 4 * 1.71 * this.q3 + 5 * 1.71) / (4 + 2 * this.q3 + 3 * 1.71 + 3 * 1.71 / this.q3);

                this.k = -1 / (1 + this.e);
            }
            case 7, 8 -> {
                this.q3 = (double) this.z3 / 380;

                this.e = 2 * this.q3;

                this.k = -1 / (1 + this.e);
            }
            case 9 -> {
                this.a = 0.115 * this.a1 * this.l;
                this.k = -(1 - this.a) / (1 + this.a);
            }
        }
    }

    public static void main(String[] args) {
        Raschet schema = new Raschet();
        schema.vvod();
        schema.calc();
        schema.pechat();
    }
}

        /*double k;
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