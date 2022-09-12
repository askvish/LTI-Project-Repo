public boolean addProfessor(Professor professor, Login login) throws ProfessorExists {
        PreparedStatement stmt = null;
        boolean result = false;
        try {
            conn = DbUtils.getConnection();
            stmt = conn.prepareStatement(SQLConstant.CREATE_PROFESSOR, Statement.RETURN_GENERATED_KEYS);



           String name = stmt.setString(1, professor.getName());
            stmt.setString(2, professor.getMobileNumber());
            stmt.setString(3, professor.getAddress());
            stmt.setInt(4, professor.getDepartmentID());
            stmt.setInt(5, professor.getAge());



           stmt.executeUpdate();
            
            int insertedID = 0;
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                insertedID = rs.getInt(1);
            }
            
            stmt.close();
            // add condition
            if(name != null)
            {result = true;
            } else {
                throw new ProfessorExists("Professor Exists!");
            }
            return result;
            
            stmt = conn.prepareStatement(SQLConstant.CREATE_USER);
            stmt.setString(1, login.getUsername());
            stmt.setString(2, login.getPassword());
            stmt.setInt(3, 2);
            stmt.setInt(4, insertedID);
            
            stmt.executeUpdate();



           stmt.close();



       } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
         } finally {
            // finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {



           }        
        }
    }