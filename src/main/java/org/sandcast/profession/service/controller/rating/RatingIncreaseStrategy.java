/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sandcast.profession.service.controller.rating;

/**
 *
 * @author Matthew
 */
public interface RatingIncreaseStrategy {

    int increase(int oldValue);
}
