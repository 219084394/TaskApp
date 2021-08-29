package za.ac.cput.Entity;

public class Login {
        private int studentNumber;
        private String password;

        private Login (Builder builder){
            this.studentNumber = builder.studentNumber;
            this.password = builder.password;
        }
        public int getStudentNumber() {
            return studentNumber;
        }

        public String getPassword() {
            return password;
        }

        @Override
        public String toString() {
            return "Login{" +
                    "studentNumber=" + studentNumber +
                    ", password='" + password + '\'' +
                    '}';
        }

        public static class Builder{
            private int studentNumber;
            private String password;

            public Builder setStudentNumber(int studentNumber) {
                this.studentNumber = studentNumber;
                return this;
            }

            public Builder setPassword(String password) {
                this.password = password;
                return this;
            }
            public Login build(){
                return new Login(this);
            }
            public Builder copy(Login login){
                this.studentNumber = login.studentNumber;
                this.password = login.password;

                return this;
            }
        }
}
