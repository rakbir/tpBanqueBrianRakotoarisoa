/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanquebrianrakotoarisoa.exception;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = false)
public class ConcurrentAccessException extends RuntimeException {

  public ConcurrentAccessException(String message) {
    super(message);
  }
}